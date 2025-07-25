package com.guilhermeramos31.springbootjuniorcase.repositories.interfaces;

import com.guilhermeramos31.springbootjuniorcase.model.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryJpaRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
}
