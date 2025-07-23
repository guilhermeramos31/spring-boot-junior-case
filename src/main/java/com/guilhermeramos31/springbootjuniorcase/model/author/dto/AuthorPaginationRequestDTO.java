package com.guilhermeramos31.springbootjuniorcase.model.author.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorPaginationRequestDTO {
    @Min(value = 1)
    private int page;

    @Min(value = 10)
    private int limit;

    @Pattern(regexp = "ASC|DESC", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Direction must be ASC or DESC")
    private String direction;
}
