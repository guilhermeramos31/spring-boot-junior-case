package com.guilhermeramos31.springbootjuniorcase.repositories.interfaces;

import com.guilhermeramos31.springbootjuniorcase.model.category.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CategoryRepository {
    Page<Category> findAll(Pageable pageable);
    Category create(Category category);
    Optional<Category> findByName(String name);
    Optional<Category> findById(long id);
}
