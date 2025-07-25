package com.guilhermeramos31.springbootjuniorcase.model.category.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDTO {
    @Schema(description = "Category identifier", example = "1")
    private Long id;

    @Schema(description = "Category name", example = "Romance")
    private String name;

    @Schema(description = "Description of what the category is",
            example = "Books that focus on romantic relationships.")
    private String description;
}
