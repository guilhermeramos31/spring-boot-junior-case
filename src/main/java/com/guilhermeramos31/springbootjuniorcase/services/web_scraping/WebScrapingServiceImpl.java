package com.guilhermeramos31.springbootjuniorcase.services.web_scraping;

import com.guilhermeramos31.springbootjuniorcase.model.author.dto.AuthorRequestDTO;
import com.guilhermeramos31.springbootjuniorcase.model.book.dto.BookRequestDTO;
import com.guilhermeramos31.springbootjuniorcase.model.book.dto.BookResponseDTO;
import com.guilhermeramos31.springbootjuniorcase.model.category.dto.CategoryRequestDTO;
import com.guilhermeramos31.springbootjuniorcase.services.author.interfaces.AuthorService;
import com.guilhermeramos31.springbootjuniorcase.services.book.interfaces.BookService;
import com.guilhermeramos31.springbootjuniorcase.services.category.interfaces.CategoryService;
import com.guilhermeramos31.springbootjuniorcase.services.web_scraping.interfaces.WebScrapingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.htmlunit.WebClient;
import org.htmlunit.html.HtmlElement;
import org.htmlunit.html.HtmlPage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class WebScrapingServiceImpl implements WebScrapingService {
    @Value("${scraping.amazon.url}")
    private String amazonUrl;

    @Value("${web-scraping.default_category_name}")
    private String defaultCategoryName;

    @Value("${web-scraping.default_category_description}")
    private String defaultCategoryDescription;

    private final WebClient webClient;

    private final AuthorService authorService;

    private final CategoryService categoryService;

    private final BookService bookService;

    private HtmlPage connection(String idBook) {
        try {
            return webClient.getPage(amazonUrl.concat(idBook));
        } catch (IOException exception) {
            throw new WebScrapingException("Failed to connect to Amazon page for book ID: " + idBook, exception);
        }
    }

    private HtmlPage page(String id) {
        return connection(id);
    }

    private <T extends HtmlElement>Optional<T> getHtmlElement(HtmlPage page, String string) {
        return Optional.ofNullable(page.getFirstByXPath(string));
    }

    private AuthorRequestDTO getAuthorScraping(HtmlPage page) {
        var author = new AuthorRequestDTO();

        this.getHtmlElement(page, "//span[@class='author notFaded']/a[@class='a-link-normal']")
                .ifPresent(e -> author.setName(e.asNormalizedText().trim()));

        return  author;
    }

    private CategoryRequestDTO getCategoryScraping() {
        var category = new CategoryRequestDTO();
        category.setName(defaultCategoryName);
        category.setDescription(defaultCategoryDescription);

        return category;
    }

    private BookRequestDTO getBookScraping(HtmlPage page) {
        var book = new BookRequestDTO();

        this.getHtmlElement(page, "//span[@id='productTitle']")
                .ifPresent(e -> book.setTitle(e.asNormalizedText().trim()));

        this.getHtmlElement(page, "//span[contains(text(), 'ISBN-13')]/following-sibling::span")
                .ifPresent(e -> book.setIsbn(e.asNormalizedText().replace("-", "").trim()));

        this.getHtmlElement(page, "//span[contains(text(), 'Data da publicação')]/following-sibling::span")
                .ifPresent(e -> {
            try {
                var formatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
                var data = LocalDate.parse(e.asNormalizedText().trim(), formatter);
                book.setYearPublished(data.getYear());
            } catch (DateTimeParseException ex) {
                log.atError().log("Error parsing publication date: " + e.asNormalizedText() + " - " + ex.getMessage());
            }
        });

        this.getHtmlElement(page, "//span[@class='a-size-base a-color-price a-color-price' and @aria-label]")
                .ifPresent(e -> {
                    var priceText = e.asNormalizedText().replace("R$", "").replace(",", ".").trim();
                    try {
                        book.setPrice(new BigDecimal(priceText));
                    } catch (NumberFormatException ex) {
                        log.atError().log("Error parsing price: " + priceText + " - " + ex.getMessage());
                    }
                });

        return book;
    }

    @Override
    public BookResponseDTO extract(String id) {
        var page = page(id);
        var scrapedCategoria = getCategoryScraping();
        var scrapedAuthor = getAuthorScraping(page);
        var scrapedBook = getBookScraping(page);

        var category = this.categoryService.create(scrapedCategoria);
        var author = this.authorService.save(scrapedAuthor);
        scrapedBook.setAuthor(author.getId());
        scrapedBook.setCategory(category.getId());

        return this.bookService.create(scrapedBook);
    }
}
