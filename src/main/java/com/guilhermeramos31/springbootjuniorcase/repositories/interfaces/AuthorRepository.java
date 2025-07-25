package com.guilhermeramos31.springbootjuniorcase.repositories.interfaces;

import com.guilhermeramos31.springbootjuniorcase.model.author.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public interface AuthorRepository {
    Author create(Author author);
    Optional<Author> findById(Long id);
    Author update(Author author);
    void delete(Long id);
    Page<Author> findAll(Pageable pageable);
}
