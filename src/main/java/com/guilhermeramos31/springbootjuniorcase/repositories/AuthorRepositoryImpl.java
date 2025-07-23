package com.guilhermeramos31.springbootjuniorcase.repositories;

import com.guilhermeramos31.springbootjuniorcase.model.author.Author;
import com.guilhermeramos31.springbootjuniorcase.repositories.interfaces.AuthorJpaRepository;
import com.guilhermeramos31.springbootjuniorcase.repositories.interfaces.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthorRepositoryImpl implements AuthorRepository {
    private final AuthorJpaRepository repository;

    private Author save(Author author) {
        return repository.save(author);
    }

    public Author create(Author author) {
        return this.save(author);
    }

    public Optional<Author> findById(Long id) {
        return repository.findById(id);
    }

    public Author update(Author author) {
        return this.save(author);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Page<Author> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
