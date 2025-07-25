package com.guilhermeramos31.springbootjuniorcase.model.base.pagination;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BasePagination<T> {

    @Schema(description = "List with pagination content")
    private List<T> content;

    @Schema(description = "Page where this is", example = "1")
    private int pageNumber;

    @Schema(description = "Number of elements per page",
            example = "10")
    private int pageSize;

    @Schema(description = "Total number of elements in the bank",
            example = "10")
    private long totalElements;

    @Schema(description = "Total pages", example = "1")
    private int totalPages;

    @Schema(description = "If the current page is the last", example = "false")
    private boolean isLast;

    @Schema(description = "If the current page is the first", example = "false")
    private boolean isFirst;

    @Schema(description = "Total number of elements in the current page", example = "5")
    private long numberOfElements;
}
