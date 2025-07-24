package com.guilhermeramos31.springbootjuniorcase.service.book.interfaces;

import com.guilhermeramos31.springbootjuniorcase.model.book.dto.BookPaginationRequestDTO;
import com.guilhermeramos31.springbootjuniorcase.model.book.dto.BookPaginationResponseDTO;
import com.guilhermeramos31.springbootjuniorcase.model.book.dto.BookRequestDTO;
import com.guilhermeramos31.springbootjuniorcase.model.book.dto.BookResponseDTO;

public interface BookService {
    BookResponseDTO create(BookRequestDTO requestDTO);
    BookResponseDTO findById(long id);
    BookResponseDTO update(long id, BookRequestDTO requestDTO);
    void delete(long id);
    BookPaginationResponseDTO findAll(BookPaginationRequestDTO pagination);
    BookResponseDTO findByTitle(String title);
}
