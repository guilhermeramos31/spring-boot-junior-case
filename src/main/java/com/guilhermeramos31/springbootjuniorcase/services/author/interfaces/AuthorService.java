package com.guilhermeramos31.springbootjuniorcase.services.author.interfaces;

import com.guilhermeramos31.springbootjuniorcase.model.author.Author;
import com.guilhermeramos31.springbootjuniorcase.model.author.dto.AuthorPaginationRequestDTO;
import com.guilhermeramos31.springbootjuniorcase.model.author.dto.AuthorPaginationResponseDTO;
import com.guilhermeramos31.springbootjuniorcase.model.author.dto.AuthorRequestDTO;
import com.guilhermeramos31.springbootjuniorcase.model.author.dto.AuthorResponseDTO;
import com.guilhermeramos31.springbootjuniorcase.model.book.dto.BookResponseDTO;

import java.util.List;

public interface AuthorService {
    AuthorResponseDTO save(AuthorRequestDTO authorRequestDTO);
    AuthorResponseDTO findById(Long id);
    AuthorResponseDTO update(Long id, AuthorRequestDTO authorRequestDTO);
    void deleteById(Long id);
    AuthorPaginationResponseDTO findAll(AuthorPaginationRequestDTO pagination);
    Author getAuthorById(long id);
    List<BookResponseDTO> findBookByAuthorId(long id);
}
