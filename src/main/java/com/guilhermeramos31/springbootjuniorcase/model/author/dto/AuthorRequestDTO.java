package com.guilhermeramos31.springbootjuniorcase.model.author.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "Author's name", example = "Marcelino")
    private String name;

    @Email
    @Schema(description = "Valid author email", example = "marcelino.silva@example.com")
    private String email;

    @Schema(description = "Author's date of birth (ISO format)", example = "1990-07-22")
    private LocalDate birthDate;
}