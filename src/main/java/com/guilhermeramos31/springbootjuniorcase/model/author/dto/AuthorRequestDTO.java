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

    @Schema(description = "Nome completo do autor", example = "Marcelino")
    @NotBlank
    private String name;

    @Schema(description = "E-mail válido do autor", example = "marcelino.silva@example.com")
    @Email
    @NotBlank
    private String email;

    @Schema(description = "Data de nascimento do autor (formato ISO)", example = "1990-07-22")
    private LocalDate birthDate;
}