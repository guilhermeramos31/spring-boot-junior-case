package com.guilhermeramos31.springbootjuniorcase.services.author;

import com.guilhermeramos31.springbootjuniorcase.model.author.Author;
import com.guilhermeramos31.springbootjuniorcase.model.author.dto.AuthorPaginationRequestDTO;
import com.guilhermeramos31.springbootjuniorcase.model.author.dto.AuthorPaginationResponseDTO;
import com.guilhermeramos31.springbootjuniorcase.model.author.dto.AuthorRequestDTO;
import com.guilhermeramos31.springbootjuniorcase.model.author.dto.AuthorResponseDTO;
import com.guilhermeramos31.springbootjuniorcase.model.author.mapper.AuthorMapper;
import com.guilhermeramos31.springbootjuniorcase.model.book.dto.BookResponseDTO;
import com.guilhermeramos31.springbootjuniorcase.model.book.mapper.BookMapper;
import com.guilhermeramos31.springbootjuniorcase.repositories.interfaces.AuthorRepository;
import com.guilhermeramos31.springbootjuniorcase.services.author.interfaces.AuthorService;
import com.guilhermeramos31.springbootjuniorcase.utils.PaginationUtil;
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
    private final AuthorMapper mapper;
    private final BookMapper bookMapper;

    private Author getAuthorById(Long id) {
        return this.authorRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Author not found"));
    }

    @Override
    public AuthorResponseDTO save(AuthorRequestDTO authorRequestDTO) {
        var author = this.authorRepository.create(mapper.toAuthor(authorRequestDTO));

        return this.mapper.toDTO(author);
    }

    @Override
    public AuthorResponseDTO findById(Long id) {
        var author = this.getAuthorById(id);

        return this.mapper.toDTO(author);
    }

    @Override
    public AuthorResponseDTO update(Long id, AuthorRequestDTO authorRequestDTO) {
        var author = this.getAuthorById(id);

        author = this.mapper.toAuthor(authorRequestDTO);
        author.setId(id);
        author =  this.authorRepository.update(author);

        return this.mapper.toDTO(author);
    }

    @Override
    public void deleteById(Long id) {
        this.findById(id);
        this.authorRepository.delete(id);
    }

    @Override
    public AuthorPaginationResponseDTO findAll(AuthorPaginationRequestDTO pagination) {
        var paginationResponse = new AuthorPaginationResponseDTO();
        var sortDirection = "DESC".equalsIgnoreCase(pagination.getDirection()) ? Sort.Direction.DESC : Sort.Direction.ASC;
        var authorsPagination = this.authorRepository.findAll(
                PageRequest.of(pagination.getPage() - 1,
                        pagination.getLimit(), Sort.by(sortDirection,
                                "name")));

        return PaginationUtil.buildPaginationResponse(
                authorsPagination,
                paginationResponse,
                pagination,
                mapper::toDTO
        );
    }

    @Override
    public Author getAuthorById(long id) {
        return this.authorRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Author not found"));
    }

    @Override
    public List<BookResponseDTO> findBookByAuthorId(long id) {
        var author = this.getAuthorById(id);

        return author.getBooks().stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList()) ;
    }
}
