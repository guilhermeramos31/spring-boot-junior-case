package com.guilhermeramos31.springbootjuniorcase.model.category.mapper;

import com.guilhermeramos31.springbootjuniorcase.model.category.Category;
import com.guilhermeramos31.springbootjuniorcase.model.category.dto.CategoryRequestDTO;
import com.guilhermeramos31.springbootjuniorcase.model.category.dto.CategoryResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoryMapperImpl implements CategoryMapper {
    private final ModelMapper modelMapper;

    @Override
    public Category toModel(CategoryRequestDTO categoryRequestDTO) {
        return modelMapper.map(categoryRequestDTO, Category.class);
    }

    @Override
    public CategoryResponseDTO toDTO(Category category) {
        return modelMapper.map(category, CategoryResponseDTO.class);
    }

    @Override
    public List<CategoryResponseDTO> toDTO(List<Category> categories) {
        return categories.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
