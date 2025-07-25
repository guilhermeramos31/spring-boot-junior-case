package com.guilhermeramos31.springbootjuniorcase.model.category.mapper;

import com.guilhermeramos31.springbootjuniorcase.model.category.Category;
import com.guilhermeramos31.springbootjuniorcase.model.category.dto.CategoryRequestDTO;
import com.guilhermeramos31.springbootjuniorcase.model.category.dto.CategoryResponseDTO;

import java.util.List;

public interface CategoryMapper {
    Category toModel(CategoryRequestDTO categoryRequestDTO);
    CategoryResponseDTO toDTO(Category category);
    List<CategoryResponseDTO> toDTO(List<Category> categories);
}
