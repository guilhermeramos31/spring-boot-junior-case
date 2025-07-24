package com.guilhermeramos31.springbootjuniorcase.model.book.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookRequestDTO {
    @NotBlank
    private String title;

    @Size(min = 10, max = 13)
    private String isbn;

    private int yearPublished;

    @NotNull
    @PositiveOrZero(message = "Price must be a positive value or zero")
    private double price;

    @NotNull
    private long author;

    @NotNull
    private long category;
}
