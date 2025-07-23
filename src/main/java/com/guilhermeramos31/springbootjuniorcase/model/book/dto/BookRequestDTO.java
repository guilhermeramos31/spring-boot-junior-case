package com.guilhermeramos31.springbootjuniorcase.model.book.dto;

import jakarta.validation.constraints.NotBlank;
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

    private double price;

    private long author;

    private long category;
}
