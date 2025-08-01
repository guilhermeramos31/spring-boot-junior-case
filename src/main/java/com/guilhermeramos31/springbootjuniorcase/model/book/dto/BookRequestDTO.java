package com.guilhermeramos31.springbootjuniorcase.model.book.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestDTO {
    @NotBlank(message = "Title cannot be empty or null")
    @Schema(description = "Book title", example = "A Ditadura Envergonhada")
    private String title;

    @Size(min = 10, max = 13)
    @Schema(description = "International Standard Book Number", example = "9788535902778")
    private String isbn;

    @Min(value = 1, message = "Year published must be a positive number")
    @Schema(description = "Year of publication of the book", example = "2002")
    private Integer yearPublished;

    @NotNull
    @PositiveOrZero(message = "Price must be a positive value or zero")
    @Schema(description = "Book price", example = "25.00")
    private BigDecimal price;

    @NotNull
    @Schema(description = "Author identifier", example = "1")
    private Long author;

    @NotNull
    @Schema(description = "Category identifier", example = "1")
    private Long category;
}
