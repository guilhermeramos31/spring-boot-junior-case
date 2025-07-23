package com.guilhermeramos31.springbootjuniorcase.controller;

import com.guilhermeramos31.springbootjuniorcase.model.author.dto.AuthorRequestDTO;
import com.guilhermeramos31.springbootjuniorcase.model.author.dto.AuthorResponseDTO;
import com.guilhermeramos31.springbootjuniorcase.service.author.interfaces.AuthorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

    @PostMapping
    public ResponseEntity<AuthorResponseDTO> createAuthor(@Valid @RequestBody AuthorRequestDTO authorDTO) {
        var author = authorService.save(authorDTO);
        var location = URI.create("/authors/" + author.getId());
        return ResponseEntity.created(location).body(author);
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponseDTO>> findAllAuthors(
            @RequestParam(value="page", defaultValue = "1") int page,
            @RequestParam(value="limit", defaultValue = "10") int limit,
            @RequestParam(value="direction", defaultValue = "asc") String direction)
    {
        return ResponseEntity.ok().body(authorService.findAll(page, limit, direction));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> findAuthor(@PathVariable Long id) {
        return ResponseEntity.ok().body(authorService.findById(id));
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
