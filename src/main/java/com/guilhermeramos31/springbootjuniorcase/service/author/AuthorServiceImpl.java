package com.guilhermeramos31.springbootjuniorcase.service.author;

import com.guilhermeramos31.springbootjuniorcase.model.author.dto.AuthorRequestDTO;
import com.guilhermeramos31.springbootjuniorcase.model.author.dto.AuthorResponseDTO;
import com.guilhermeramos31.springbootjuniorcase.model.author.mapper.AuthorMapper;
import com.guilhermeramos31.springbootjuniorcase.repositories.interfaces.AuthorRepository;
import com.guilhermeramos31.springbootjuniorcase.service.author.interfaces.AuthorService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.Long;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorResponseDTO save(AuthorRequestDTO authorRequestDTO) {
        var author = authorRepository.create(authorMapper.toAuthor(authorRequestDTO));

        return authorMapper.toDTO(author);
    }

    public AuthorResponseDTO findById(Long id) {
        var author = authorRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Author not found"));

        return authorMapper.toDTO(author);
    }

    public AuthorResponseDTO update(Long id, AuthorRequestDTO authorRequestDTO) {
        var author = authorMapper.toAuthor(authorRequestDTO);
        author =  authorRepository.update(author);

        return authorMapper.toDTO(author);
    }

    public void deleteById(Long id) {
        authorRepository.delete(id);
    }

    public List<AuthorResponseDTO> findAll(int page, int limit, String direction) {
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        var pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "name"));

        var authors = authorRepository.findAll(pageable);

        return authorMapper.toDTO(authors.stream().collect(Collectors.toList()));
    }
}
