package com.guilhermeramos31.springbootjuniorcase.service.book;

import com.guilhermeramos31.springbootjuniorcase.model.book.Book;
import com.guilhermeramos31.springbootjuniorcase.model.book.dto.BookPaginationRequestDTO;
import com.guilhermeramos31.springbootjuniorcase.model.book.dto.BookPaginationResponseDTO;
import com.guilhermeramos31.springbootjuniorcase.model.book.dto.BookRequestDTO;
import com.guilhermeramos31.springbootjuniorcase.model.book.dto.BookResponseDTO;
import com.guilhermeramos31.springbootjuniorcase.model.book.mapper.BookMapper;
import com.guilhermeramos31.springbootjuniorcase.repositories.interfaces.BookRepository;
import com.guilhermeramos31.springbootjuniorcase.service.book.interfaces.BookService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository repository;
    private final BookMapper mapper;

    @Override
    public BookResponseDTO create(BookRequestDTO requestDTO) {
        this.priceIsPositive(requestDTO.getPrice());

        var book = mapper.toModel(requestDTO);
        book = repository.create(book);

        return mapper.toDTO(book);
    }

    private void priceIsPositive(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price must be greater than or equal to zero");
        }
    }

    @Override
    public BookResponseDTO findById(long id) {
        return mapper.toDTO(this.findBookById(id));
    }

    private Book findBookById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));
    }

    @Override
    public BookResponseDTO update(long id, BookRequestDTO requestDTO) {
        this.priceIsPositive(requestDTO.getPrice());

        var book = this.findBookById(id);
        book =  repository.create(mapper.toModel(requestDTO));

        return mapper.toDTO(book);
    }

    @Override
    public void delete(long id) {
        this.findBookById(id);
        repository.delete(id);
    }

    @Override
    public BookPaginationResponseDTO findAll(BookPaginationRequestDTO pagination) {
        var paginationResponse = new BookPaginationResponseDTO();
        var sortDirection = "DESC".equalsIgnoreCase(pagination.getDirection()) ? Sort.Direction.DESC : Sort.Direction.ASC;
        var booksPagination = repository.findAll(
                PageRequest.of(pagination.getPage() - 1,
                        pagination.getLimit(), Sort.by(sortDirection,
                                "name")));

        paginationResponse.setContent(mapper.toDTO(booksPagination.stream().collect(Collectors.toList())));

        paginationResponse.setPageNumber(pagination.getPage());
        paginationResponse.setPageSize(pagination.getLimit());

        paginationResponse.setTotalElements(booksPagination.getTotalElements());
        paginationResponse.setTotalPages(booksPagination.getTotalPages());
        paginationResponse.setNumberOfElements(booksPagination.getContent().size());
        paginationResponse.setFirst(booksPagination.isFirst());
        paginationResponse.setLast(booksPagination.isLast());

        return paginationResponse;
    }
}
