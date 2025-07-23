package com.guilhermeramos31.springbootjuniorcase.repositories.interfaces;

import com.guilhermeramos31.springbootjuniorcase.model.book.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BookRepository {
    Book create(Book book);
    Optional<Book> findById(Long id);
    Optional<Book> findByTitle(String title);
    Book update(Book book);
    void delete(long id);
    Page<Book> findAll(Pageable pageable);
}
