package com.guilhermeramos31.springbootjuniorcase.model.author.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorRequestDTO {
    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    private LocalDate birthDate;
}
