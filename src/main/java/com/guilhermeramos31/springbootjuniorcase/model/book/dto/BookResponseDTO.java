package com.guilhermeramos31.springbootjuniorcase.model.book.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseDTO {
    private Long id;

    private String title;

    private String isbn;

    private Integer yearPublished;

    private BigDecimal price;

    private Long author;

    private Long category;
}
