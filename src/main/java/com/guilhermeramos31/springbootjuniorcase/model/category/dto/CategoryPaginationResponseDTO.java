package com.guilhermeramos31.springbootjuniorcase.model.category.dto;

import com.guilhermeramos31.springbootjuniorcase.model.base.pagination.BasePagination;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryPaginationResponseDTO extends BasePagination<CategoryResponseDTO> {
}
