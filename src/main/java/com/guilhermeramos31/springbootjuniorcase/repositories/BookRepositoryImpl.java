package com.guilhermeramos31.springbootjuniorcase.repositories;

import com.guilhermeramos31.springbootjuniorcase.model.book.Book;
import com.guilhermeramos31.springbootjuniorcase.repositories.interfaces.BookJpaRepository;
import com.guilhermeramos31.springbootjuniorcase.repositories.interfaces.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {
    private final BookJpaRepository repository;

    private Book saveBook(Book book) {
        return repository.save(book);
    }

    @Override
    public Book create(Book book) {
        return this.saveBook(book);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Book> findByTitle(String title) {
        return repository.findByTitle(title);
    }

    @Override
    public Book update(Book book) {
        return this.saveBook(book);
    }

    @Override
    public void delete(long id) {
        this.repository.deleteById(id);
    }

    @Override
    public Page<Book> findAll(Specification<Book> specification, Pageable pageable) {
        return repository.findAll(specification, pageable);
    }
}
