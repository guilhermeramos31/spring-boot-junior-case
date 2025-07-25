package com.guilhermeramos31.springbootjuniorcase.controller;

import com.guilhermeramos31.springbootjuniorcase.model.book.dto.BookPaginationRequestDTO;
import com.guilhermeramos31.springbootjuniorcase.model.book.dto.BookPaginationResponseDTO;
import com.guilhermeramos31.springbootjuniorcase.model.book.dto.BookRequestDTO;
import com.guilhermeramos31.springbootjuniorcase.model.book.dto.BookResponseDTO;
import com.guilhermeramos31.springbootjuniorcase.services.book.interfaces.BookService;
import com.guilhermeramos31.springbootjuniorcase.utils.HttpHeadersUtil;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    private final HttpHeadersUtil headersUtil;

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Book created"),
            @ApiResponse(responseCode = "400", description = "Book not created because of some bad request")
    })
    public ResponseEntity<BookResponseDTO> createBook(@Valid @RequestBody BookRequestDTO bookRequestDTO) {
        var book = bookService.create(bookRequestDTO);
        var location = URI.create("/books/" + book.getId());
        return ResponseEntity.created(location).body(book);
    }

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200")
    })
    public ResponseEntity<BookPaginationResponseDTO> getAllBooks(@Valid @ModelAttribute BookPaginationRequestDTO pagination) {
        var books = bookService.findAll(pagination);
        var headers = headersUtil.setPaginationHeaders(books);

        return ResponseEntity.ok().headers(headers).body(books);
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book found"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    public ResponseEntity<BookResponseDTO> getBook(@PathVariable long id) {
        return ResponseEntity.ok(bookService.findById(id));
    }

    @GetMapping("/search")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book found"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    public ResponseEntity<BookResponseDTO> findByTitle(@RequestParam @Valid @NotBlank String title) {
        return ResponseEntity.ok(bookService.findByTitle(title));
    }

    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Book updated"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable long id, @Valid @RequestBody BookRequestDTO bookRequestDTO) {
        return ResponseEntity.ok(bookService.update(id, bookRequestDTO));
    }

    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Book deleted"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    public ResponseEntity<BookResponseDTO> deleteBook(@PathVariable long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
