package com.guilhermeramos31.springbootjuniorcase.controller;

import com.guilhermeramos31.springbootjuniorcase.model.book.dto.BookResponseDTO;
import com.guilhermeramos31.springbootjuniorcase.model.category.dto.CategoryPaginationRequestDTO;
import com.guilhermeramos31.springbootjuniorcase.model.category.dto.CategoryPaginationResponseDTO;
import com.guilhermeramos31.springbootjuniorcase.model.category.dto.CategoryRequestDTO;
import com.guilhermeramos31.springbootjuniorcase.model.category.dto.CategoryResponseDTO;
import com.guilhermeramos31.springbootjuniorcase.services.category.interfaces.CategoryService;
import com.guilhermeramos31.springbootjuniorcase.utils.HttpHeadersUtil;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@Tag(name = "Category")
@RequiredArgsConstructor
@RequestMapping("categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final HttpHeadersUtil headersUtil;

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category created")
    })
    public ResponseEntity<CategoryResponseDTO> create(@RequestBody CategoryRequestDTO  categoryRequestDTO) {
        var category = categoryService.create(categoryRequestDTO);
        var location = URI.create("/categories/" + category.getId());

        return ResponseEntity.created(location).body(category);
    }

    @GetMapping
    @ApiResponse(responseCode = "200")
    public ResponseEntity<CategoryPaginationResponseDTO> getAll(@Valid @ModelAttribute CategoryPaginationRequestDTO pagination) {
        var category = categoryService.findAll(pagination);
        var headers = headersUtil.setPaginationHeaders(category);

        return ResponseEntity.ok().headers(headers).body(category);
    }

    @GetMapping("/{id}/books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Books found"),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    public ResponseEntity<List<BookResponseDTO>> getBook(@PathVariable Long id) {
        return ResponseEntity.ok().body(categoryService.findBookByCategoryId(id));
    }
}
