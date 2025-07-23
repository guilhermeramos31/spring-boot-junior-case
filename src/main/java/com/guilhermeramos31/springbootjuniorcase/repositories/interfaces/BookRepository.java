package com.guilhermeramos31.springbootjuniorcase.repositories.interfaces;

import com.guilhermeramos31.springbootjuniorcase.model.book.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BookRepository {
    Book create(Book book);
    Optional<Book> findById(Long id);
    Optional<Book> findByName(String name);
    Book update(Book book);
    void delete(Book book);
    Page<Book> findAll(Pageable pageable);
}
