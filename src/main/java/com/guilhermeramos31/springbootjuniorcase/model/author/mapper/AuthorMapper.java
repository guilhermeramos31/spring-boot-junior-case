package com.guilhermeramos31.springbootjuniorcase.model.author.mapper;

import com.guilhermeramos31.springbootjuniorcase.model.author.Author;
import com.guilhermeramos31.springbootjuniorcase.model.author.dto.AuthorPaginationRequestDTO;
import com.guilhermeramos31.springbootjuniorcase.model.author.dto.AuthorRequestDTO;
import com.guilhermeramos31.springbootjuniorcase.model.author.dto.AuthorResponseDTO;
import com.guilhermeramos31.springbootjuniorcase.model.book.dto.BookPaginationRequestDTO;

import java.util.Collection;
import java.util.List;

public interface AuthorMapper {
    Author toAuthor(AuthorRequestDTO author);

    AuthorResponseDTO toDTO(Author author);
    List<AuthorResponseDTO> toDTO(Collection<Author> authors);
    BookPaginationRequestDTO toBookPagination(AuthorPaginationRequestDTO authorPaginationRequestDTO);
}
