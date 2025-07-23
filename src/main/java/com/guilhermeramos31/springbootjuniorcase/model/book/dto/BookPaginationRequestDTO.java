package com.guilhermeramos31.springbootjuniorcase.model.book.dto;

import com.guilhermeramos31.springbootjuniorcase.model.base.pagination.BasePaginationDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookPaginationRequestDTO extends BasePaginationDTO {
    private String filter;
}
