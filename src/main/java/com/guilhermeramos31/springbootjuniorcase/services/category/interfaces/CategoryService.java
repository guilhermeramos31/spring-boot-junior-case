package com.guilhermeramos31.springbootjuniorcase.services.category.interfaces;

import com.guilhermeramos31.springbootjuniorcase.model.book.dto.BookResponseDTO;
import com.guilhermeramos31.springbootjuniorcase.model.category.Category;
import com.guilhermeramos31.springbootjuniorcase.model.category.dto.CategoryPaginationRequestDTO;
import com.guilhermeramos31.springbootjuniorcase.model.category.dto.CategoryPaginationResponseDTO;
import com.guilhermeramos31.springbootjuniorcase.model.category.dto.CategoryRequestDTO;
import com.guilhermeramos31.springbootjuniorcase.model.category.dto.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {
    CategoryResponseDTO create(CategoryRequestDTO categoryRequestDTO);
    CategoryPaginationResponseDTO findAll(CategoryPaginationRequestDTO pagination);
    Category getCategoryById(long category);
    List<BookResponseDTO> findBookByCategoryId(long id);
}
