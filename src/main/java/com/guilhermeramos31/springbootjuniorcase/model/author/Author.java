package com.guilhermeramos31.springbootjuniorcase.model.author;

import com.guilhermeramos31.springbootjuniorcase.model.base.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Author extends BaseEntity {
    private String name;
    private String email;
    private LocalDate birthDate;
}
