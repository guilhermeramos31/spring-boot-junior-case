package com.guilhermeramos31.springbootjuniorcase.utils;

import com.guilhermeramos31.springbootjuniorcase.model.base.pagination.BasePagination;
import com.guilhermeramos31.springbootjuniorcase.model.base.pagination.BasePaginationDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;

public class PaginationUtil {
    public static <T, R, P extends BasePagination<R>> P buildPaginationResponse(
            Page<T> pageData,
            P paginationResponse,
            BasePaginationDTO pagination,
            Function<List<T>, List<R>> mapper
    ){
        List<R> contentDTOs = mapper.apply(pageData.getContent());

        paginationResponse.setContent(contentDTOs);
        paginationResponse.setPageNumber(pagination.getPage());
        paginationResponse.setPageSize(pagination.getLimit());
        paginationResponse.setTotalElements(pageData.getTotalElements());
        paginationResponse.setTotalPages(pageData.getTotalPages());
        paginationResponse.setNumberOfElements(pageData.getNumberOfElements());
        paginationResponse.setFirst(pageData.isFirst());
        paginationResponse.setLast(pageData.isLast());

        return paginationResponse;
    }
}
