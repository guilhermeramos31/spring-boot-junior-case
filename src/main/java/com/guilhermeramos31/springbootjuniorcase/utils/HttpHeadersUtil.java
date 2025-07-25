package com.guilhermeramos31.springbootjuniorcase.utils;

import com.guilhermeramos31.springbootjuniorcase.model.base.pagination.BasePagination;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class HttpHeadersUtil {
    @Value("${my-app.header.page-number}")
    private String pageNumber;

    @Value("${my-app.header.page-size}")
    private String pageSize;

    @Value("${my-app.header.total-elements}")
    private String totalElements;

    @Value("${my-app.header.total-pages}")
    private String totalPages;

    @Value("${my-app.header.is-last}")
    private String isLast;

    @Value("${my-app.header.is-first}")
    private String isFirst;

    @Value("${my-app.header.number-of-elements}")
    private String numberOfElements;
    private final HttpHeaders headers =  new HttpHeaders();

    public <T> HttpHeaders setPaginationHeaders(BasePagination<T> pagination) {
        headers.add(pageNumber, String.valueOf(pagination.getPageNumber()));
        headers.add(pageSize, String.valueOf(pagination.getPageSize()));
        headers.add(isLast, String.valueOf(pagination.isLast()));
        headers.add(isFirst, String.valueOf(pagination.isFirst()));
        headers.add(numberOfElements, String.valueOf(pagination.getNumberOfElements()));
        headers.add(totalElements, String.valueOf(pagination.getTotalElements()));
        headers.add(totalPages, String.valueOf(pagination.getTotalPages()));

        return headers;
    }
}
