package com.guilhermeramos31.springbootjuniorcase.controller;

import com.guilhermeramos31.springbootjuniorcase.model.category.dto.CategoryPaginationRequestDTO;
import com.guilhermeramos31.springbootjuniorcase.model.category.dto.CategoryPaginationResponseDTO;
import com.guilhermeramos31.springbootjuniorcase.model.category.dto.CategoryRequestDTO;
import com.guilhermeramos31.springbootjuniorcase.model.category.dto.CategoryResponseDTO;
import com.guilhermeramos31.springbootjuniorcase.services.category.interfaces.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@Tag(name = "Category")
@RequiredArgsConstructor
@RequestMapping("categories")
public class CategoryController {
    private final CategoryService categoryService;

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

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> create(@RequestBody CategoryRequestDTO  categoryRequestDTO) {
        var category = categoryService.create(categoryRequestDTO);
        var location = URI.create("/categories/" + category.getId());
        return ResponseEntity.created(location).body(category);
    }

    @GetMapping
    public ResponseEntity<CategoryPaginationResponseDTO> getAll(@Valid @ModelAttribute CategoryPaginationRequestDTO pagination) {
        var category = categoryService.findAll(pagination);
        var headers = new HttpHeaders();

        headers.add(pageNumber, String.valueOf(category.getPageNumber()));
        headers.add(pageSize, String.valueOf(category.getPageSize()));
        headers.add(isLast, String.valueOf(category.isLast()));
        headers.add(isFirst, String.valueOf(category.isFirst()));
        headers.add(numberOfElements, String.valueOf(category.getNumberOfElements()));
        headers.add(totalElements, String.valueOf(category.getTotalElements()));
        headers.add(totalPages, String.valueOf(category.getTotalPages()));

        return ResponseEntity.ok().headers(headers).body(category);
    }
}
