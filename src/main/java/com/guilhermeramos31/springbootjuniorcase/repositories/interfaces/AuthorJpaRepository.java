package com.guilhermeramos31.springbootjuniorcase.repositories.interfaces;

import com.guilhermeramos31.springbootjuniorcase.model.author.Author;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthorJpaRepository extends JpaRepository<Author, Long> {
}
