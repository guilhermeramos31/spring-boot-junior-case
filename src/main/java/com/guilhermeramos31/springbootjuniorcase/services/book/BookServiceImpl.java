package com.guilhermeramos31.springbootjuniorcase.services.book;

import com.guilhermeramos31.springbootjuniorcase.model.book.Book;
import com.guilhermeramos31.springbootjuniorcase.model.book.dto.BookPaginationRequestDTO;
import com.guilhermeramos31.springbootjuniorcase.model.book.dto.BookPaginationResponseDTO;
import com.guilhermeramos31.springbootjuniorcase.model.book.dto.BookRequestDTO;
import com.guilhermeramos31.springbootjuniorcase.model.book.dto.BookResponseDTO;
import com.guilhermeramos31.springbootjuniorcase.model.book.mapper.BookMapper;
import com.guilhermeramos31.springbootjuniorcase.repositories.interfaces.BookRepository;
import com.guilhermeramos31.springbootjuniorcase.services.author.interfaces.AuthorService;
import com.guilhermeramos31.springbootjuniorcase.services.book.interfaces.BookService;
import com.guilhermeramos31.springbootjuniorcase.services.category.interfaces.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository repository;
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final BookMapper mapper;

    @Override
    public BookResponseDTO create(BookRequestDTO requestDTO) {
        this.priceIsPositive(requestDTO.getPrice());
        this.yearPublishedIsInFuture(requestDTO.getYearPublished());

        var book = mapper.toModel(requestDTO);
        book.setAuthor(authorService.getAuthorById(requestDTO.getAuthor()));
        book.setCategory(categoryService.getCategoryById(requestDTO.getCategory()));
        book = repository.create(book);

        return mapper.toDTO(book);
    }

    private void priceIsPositive(BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price must be greater than or equal to zero");
        }
    }

    private void yearPublishedIsInFuture(Integer yearPublished) {
        if (yearPublished == null || yearPublished > LocalDate.now().getYear()) {
            throw new IllegalArgumentException("Invalid year published");
        }
    }

    @Override
    public BookResponseDTO findById(long id) {
        return mapper.toDTO(this.findBookById(id));
    }

    private Book findBookById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));
    }

    @Override
    public BookResponseDTO update(long id, BookRequestDTO requestDTO) {
        this.priceIsPositive(requestDTO.getPrice());
        this.yearPublishedIsInFuture(requestDTO.getYearPublished());

        var book = this.findBookById(id);
        var bookUpdated = mapper.toModel(requestDTO);
        bookUpdated.setId(id);
        bookUpdated.setAuthor(book.getAuthor());
        bookUpdated.setCategory(book.getCategory());
        bookUpdated = repository.update(bookUpdated);

        return mapper.toDTO(bookUpdated);
    }

    @Override
    public void delete(long id) {
        this.findBookById(id);
        repository.delete(id);
    }

    @Override
    public BookPaginationResponseDTO findAll(BookPaginationRequestDTO pagination) {
        var paginationResponse = new BookPaginationResponseDTO();
        var sortDirection = "DESC".equalsIgnoreCase(pagination.getDirection()) ? Sort.Direction.DESC : Sort.Direction.ASC;

        var booksPagination = repository.findAll(pagination.toSpecification(),
                PageRequest.of(pagination.getPage() - 1,
                        pagination.getLimit(), Sort.by(sortDirection,
                                "title")));

        paginationResponse.setContent(mapper.toDTO(booksPagination.stream().collect(Collectors.toList())));

        paginationResponse.setPageNumber(pagination.getPage());
        paginationResponse.setPageSize(pagination.getLimit());

        paginationResponse.setTotalElements(booksPagination.getTotalElements());
        paginationResponse.setTotalPages(booksPagination.getTotalPages());
        paginationResponse.setNumberOfElements(booksPagination.getContent().size());
        paginationResponse.setFirst(booksPagination.isFirst());
        paginationResponse.setLast(booksPagination.isLast());

        return paginationResponse;
    }

    @Override
    public BookResponseDTO findByTitle(String title) {
        var  book = repository.findByTitle(title).orElseThrow(() -> new EntityNotFoundException("Book not found with title: " + title));

        return mapper.toDTO(book);
    }
}
