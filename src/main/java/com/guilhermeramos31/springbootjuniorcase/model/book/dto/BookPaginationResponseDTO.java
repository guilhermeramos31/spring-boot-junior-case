package com.guilhermeramos31.springbootjuniorcase.model.book.dto;

import com.guilhermeramos31.springbootjuniorcase.model.base.pagination.BasePagination;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class BookPaginationResponseDTO extends BasePagination<BookResponseDTO> {
}
