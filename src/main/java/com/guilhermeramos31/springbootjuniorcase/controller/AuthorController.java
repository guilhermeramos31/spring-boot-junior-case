package com.guilhermeramos31.springbootjuniorcase.controller;

import com.guilhermeramos31.springbootjuniorcase.model.author.dto.AuthorPaginationRequestDTO;
import com.guilhermeramos31.springbootjuniorcase.model.author.dto.AuthorPaginationResponseDTO;
import com.guilhermeramos31.springbootjuniorcase.model.author.dto.AuthorRequestDTO;
import com.guilhermeramos31.springbootjuniorcase.model.author.dto.AuthorResponseDTO;
import com.guilhermeramos31.springbootjuniorcase.model.book.dto.BookResponseDTO;
import com.guilhermeramos31.springbootjuniorcase.services.author.interfaces.AuthorService;
import com.guilhermeramos31.springbootjuniorcase.utils.HttpHeadersUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@Tag(name = "Author")
@RequiredArgsConstructor
@RequestMapping("authors")
public class AuthorController {
    private final AuthorService authorService;
    private final HttpHeadersUtil headersUtil;

    @PostMapping
    public ResponseEntity<AuthorResponseDTO> createAuthor(@Valid @RequestBody AuthorRequestDTO authorDTO) {
        var author = authorService.save(authorDTO);
        var location = URI.create("/authors/" + author.getId());
        return ResponseEntity.created(location).body(author);
    }

    @GetMapping
    public ResponseEntity<AuthorPaginationResponseDTO> findAllAuthors(@Valid @ModelAttribute AuthorPaginationRequestDTO pagination) {
        var authors = authorService.findAll(pagination);
        var headers = headersUtil.setPaginationHeaders(authors);

        return ResponseEntity.ok().headers(headers).body(authors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> findAuthor(@PathVariable Long id) {
        return ResponseEntity.ok().body(authorService.findById(id));
    }

    @GetMapping("/{id}/books")
    public ResponseEntity<List<BookResponseDTO>> findBook(@PathVariable long id) {
        return ResponseEntity.ok().body(authorService.findBookByAuthorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> updateAuthor(@PathVariable Long id, @Valid @RequestBody AuthorRequestDTO authorDTO) {
        var author = authorService.update(id, authorDTO);
        var location = URI.create("/authors/" + author.getId());
        return ResponseEntity.created(location).body(author);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> deleteAuthor(@PathVariable Long id) {
        authorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
