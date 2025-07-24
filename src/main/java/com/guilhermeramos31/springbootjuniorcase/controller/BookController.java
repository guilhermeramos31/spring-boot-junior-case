package com.guilhermeramos31.springbootjuniorcase.controller;

import com.guilhermeramos31.springbootjuniorcase.model.book.dto.BookPaginationRequestDTO;
import com.guilhermeramos31.springbootjuniorcase.model.book.dto.BookPaginationResponseDTO;
import com.guilhermeramos31.springbootjuniorcase.model.book.dto.BookRequestDTO;
import com.guilhermeramos31.springbootjuniorcase.model.book.dto.BookResponseDTO;
import com.guilhermeramos31.springbootjuniorcase.service.book.interfaces.BookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@Tag(name = "Book")
@RequiredArgsConstructor
@RequestMapping("books")
public class BookController {
    private final BookService bookService;

    @Value("${my-app.header.page-number}")
    private String pageNumber;

    @Value("${my-app.header.page-size}")
    private String pageSize;

    @Value("${my-app.header.total-elements}")
    private String totalElements;

    @Value("${my-app.header.total-pages}")
    private String totalPages;

    @Value("${my-app.header.is-last}")
    private String isLast;

    @Value("${my-app.header.is-first}")
    private String isFirst;

    @Value("${my-app.header.number-of-elements}")
    private String numberOfElements;

    @PostMapping
    public ResponseEntity<BookResponseDTO> createBook(@Valid @RequestBody BookRequestDTO bookRequestDTO) {
        var book = bookService.create(bookRequestDTO);
        var location = URI.create("/books/" + book.getId());
        return ResponseEntity.created(location).body(book);
    }

    @GetMapping
    public ResponseEntity<BookPaginationResponseDTO> getAllBooks(@Valid @ModelAttribute BookPaginationRequestDTO pagination) {
        var books = bookService.findAll(pagination);
        var headers = new HttpHeaders();

        headers.add(pageNumber, String.valueOf(books.getPageNumber()));
        headers.add(pageSize, String.valueOf(books.getPageSize()));
        headers.add(isLast, String.valueOf(books.isLast()));
        headers.add(isFirst, String.valueOf(books.isFirst()));
        headers.add(numberOfElements, String.valueOf(books.getNumberOfElements()));
        headers.add(totalElements, String.valueOf(books.getTotalElements()));
        headers.add(totalPages, String.valueOf(books.getTotalPages()));

        return ResponseEntity.ok().headers(headers).body(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getBook(@PathVariable long id) {
        return ResponseEntity.ok(bookService.findById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<BookResponseDTO> findByTitle(@RequestParam @Valid @NotBlank String title) {
        return ResponseEntity.ok(bookService.findByTitle(title));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable long id, @RequestBody BookRequestDTO bookRequestDTO) {
        return ResponseEntity.ok(bookService.update(id, bookRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookResponseDTO> deleteBook(@PathVariable long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
