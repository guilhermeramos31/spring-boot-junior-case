package com.guilhermeramos31.springbootjuniorcase.model.book.mapper;

import com.guilhermeramos31.springbootjuniorcase.model.book.Book;
import com.guilhermeramos31.springbootjuniorcase.model.book.dto.BookRequestDTO;
import com.guilhermeramos31.springbootjuniorcase.model.book.dto.BookResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookMapperImpl implements BookMapper {
    private final ModelMapper mapper;

    @Override
    public Book toModel(BookRequestDTO bookRequestDTO) {
        return mapper.map(bookRequestDTO, Book.class);
    }

    @Override
    public BookResponseDTO toDTO(Book book) {
        return mapper.map(book, BookResponseDTO.class);
    }

    @Override
    public List<BookResponseDTO> toDTO(Collection<Book> books) {
        return books.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
