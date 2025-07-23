package com.guilhermeramos31.springbootjuniorcase.model.author.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorResponseDTO {
    private Long id;

    private String name;

    private String email;

    private LocalDate birthDate;
}
