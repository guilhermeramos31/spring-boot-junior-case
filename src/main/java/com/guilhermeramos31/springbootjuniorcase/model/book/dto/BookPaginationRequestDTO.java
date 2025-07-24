package com.guilhermeramos31.springbootjuniorcase.model.book.dto;

import com.guilhermeramos31.springbootjuniorcase.model.base.pagination.BasePaginationDTO;
import com.guilhermeramos31.springbootjuniorcase.model.book.Book;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import static com.guilhermeramos31.springbootjuniorcase.repositories.specifications.BookSpecification.*;

@Getter
@Setter
@NoArgsConstructor
public class BookPaginationRequestDTO extends BasePaginationDTO {
    private String author;
    private String category;
    @Min(value = 1, message = "Year published must be a positive number")
    private Integer yearPublished;

    public Specification<Book> toSpecification() {
        Specification<Book> specification = (root, query, builder) -> null;

        if (author != null && !author.isBlank()) {
            specification = specification.and(hasAuthorName(author));
        }

        if (category != null && !category.isBlank()) {
            specification = specification.and(hasCategoryName(category));
        }

        if (yearPublished != null && yearPublished > 0) {
            specification = specification.and(hasYearPublished(yearPublished));
        }

        return specification;
    }}
