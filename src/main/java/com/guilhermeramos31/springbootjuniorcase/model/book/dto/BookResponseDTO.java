package com.guilhermeramos31.springbootjuniorcase.model.book.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "Book identifier", example = "1")
    private Long id;

    @Schema(description = "Book title", example = "A Ditadura Envergonhada")
    private String title;

    @Schema(description = "International Standard Book Number", example = "9788535902778")
    private String isbn;

    @Schema(description = "Year of publication of the book", example = "2002")
    private Integer yearPublished;

    @Schema(description = "Book price", example = "25.00")
    private BigDecimal price;

    @Schema(description = "Author identifier", example = "1")
    private Long author;

    @Schema(description = "Category identifier", example = "1")
    private Long category;
}
