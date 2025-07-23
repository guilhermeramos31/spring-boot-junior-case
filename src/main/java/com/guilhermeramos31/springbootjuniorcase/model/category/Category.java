package com.guilhermeramos31.springbootjuniorcase.model.category;

import com.guilhermeramos31.springbootjuniorcase.model.base.entity.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category extends BaseEntity {
    private String name;
    private String description;
}
