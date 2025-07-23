package com.guilhermeramos31.springbootjuniorcase.model.author.mapper;

import com.guilhermeramos31.springbootjuniorcase.model.author.Author;
import com.guilhermeramos31.springbootjuniorcase.model.author.dto.AuthorRequestDTO;
import com.guilhermeramos31.springbootjuniorcase.model.author.dto.AuthorResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AuthorMapperImpl implements AuthorMapper {
    private final ModelMapper modelMapper;

    @Override
    public Author toAuthor(AuthorRequestDTO authorDTO) {
        return modelMapper.map(authorDTO, Author.class);
    }

    @Override
    public AuthorResponseDTO toDTO(Author author) {
        return modelMapper.map(author, AuthorResponseDTO.class);
    }

    @Override
    public List<AuthorResponseDTO> toDTO(Collection<Author> authors) {
        return authors.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
