package com.guilhermeramos31.springbootjuniorcase.service.author;

import com.guilhermeramos31.springbootjuniorcase.model.author.dto.AuthorPaginationRequestDTO;
import com.guilhermeramos31.springbootjuniorcase.model.author.dto.AuthorPaginationResponseDTO;
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

    public AuthorPaginationResponseDTO findAll(AuthorPaginationRequestDTO pagination) {
        var paginationResponse = new AuthorPaginationResponseDTO();
        var sortDirection = "DESC".equalsIgnoreCase(pagination.getDirection()) ? Sort.Direction.DESC : Sort.Direction.ASC;
        var pageable = PageRequest.of(pagination.getPage() - 1, pagination.getLimit(), Sort.by(sortDirection, "name"));
        var authorsPagination = authorRepository.findAll(pageable);

        paginationResponse.setContent(authorMapper.toDTO(authorsPagination.stream().collect(Collectors.toList())));

        paginationResponse.setPageNumber(pagination.getPage());
        paginationResponse.setPageSize(pagination.getLimit());

        paginationResponse.setTotalElements(authorsPagination.getTotalElements());
        paginationResponse.setTotalPages(authorsPagination.getTotalPages());
        paginationResponse.setNumberOfElements(authorsPagination.getContent().size());
        paginationResponse.setFirst(authorsPagination.isFirst());
        paginationResponse.setLast(authorsPagination.isLast());

        return paginationResponse;
    }
}
