package com.guilhermeramos31.springbootjuniorcase.repositories;

import com.guilhermeramos31.springbootjuniorcase.model.category.Category;
import com.guilhermeramos31.springbootjuniorcase.repositories.interfaces.CategoryJpaRepository;
import com.guilhermeramos31.springbootjuniorcase.repositories.interfaces.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {
    private final CategoryJpaRepository repository;

    private Category save (Category category) {
        return repository.save(category);
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Category create(Category category) {
        return this.save(category);
    }

    @Override
    public Optional<Category> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Optional<Category> findById(long id) {
        return repository.findById(id);
    }
}
