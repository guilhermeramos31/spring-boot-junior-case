package com.guilhermeramos31.springbootjuniorcase.model.book.mapper;

import com.guilhermeramos31.springbootjuniorcase.model.book.Book;
import com.guilhermeramos31.springbootjuniorcase.model.book.dto.BookRequestDTO;
import com.guilhermeramos31.springbootjuniorcase.model.book.dto.BookResponseDTO;

import java.util.Collection;
import java.util.List;

public interface BookMapper {
    Book toModel(BookRequestDTO bookRequestDTO);
    BookResponseDTO toDTO(Book book);
    List<BookResponseDTO> toDTO(Collection<Book> books);
}
