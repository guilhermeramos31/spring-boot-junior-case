package com.guilhermeramos31.springbootjuniorcase.model.author.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "Identifier", example = "1", type = "Long")
    private Long id;

    @Schema(description = "Author's name", example = "Marcelino")
    private String name;

    @Schema(description = "Author's email", example = "marcelino.silva@example.com")
    private String email;

    @Schema(description = "Author's date of birth (ISO format)", example = "1990-07-22")
    private LocalDate birthDate;
}
