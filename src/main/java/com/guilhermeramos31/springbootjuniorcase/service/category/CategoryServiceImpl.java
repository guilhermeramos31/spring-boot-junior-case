package com.guilhermeramos31.springbootjuniorcase.service.category;

import com.guilhermeramos31.springbootjuniorcase.model.category.dto.CategoryPaginationRequestDTO;
import com.guilhermeramos31.springbootjuniorcase.model.category.dto.CategoryPaginationResponseDTO;
import com.guilhermeramos31.springbootjuniorcase.model.category.dto.CategoryRequestDTO;
import com.guilhermeramos31.springbootjuniorcase.model.category.dto.CategoryResponseDTO;
import com.guilhermeramos31.springbootjuniorcase.model.category.mapper.CategoryMapper;
import com.guilhermeramos31.springbootjuniorcase.repositories.interfaces.CategoryRepository;
import com.guilhermeramos31.springbootjuniorcase.service.category.interfaces.CategoryService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper mapper;

    @Override
    public CategoryResponseDTO create(CategoryRequestDTO categoryRequestDTO) {
        var category = categoryRepository.findByName(categoryRequestDTO.getName());
        if (category.isPresent()) {
            throw new EntityExistsException("Category with name " + categoryRequestDTO.getName() + " already exists");
        }

        return mapper.toDTO(categoryRepository.create(mapper.toModel(categoryRequestDTO)));
    }

    @Override
    public CategoryPaginationResponseDTO findAll(CategoryPaginationRequestDTO pagination) {
        var paginationResponse = new CategoryPaginationResponseDTO();
        var sortDirection = "DESC".equalsIgnoreCase(pagination.getDirection()) ? Sort.Direction.DESC : Sort.Direction.ASC;
        var categoryPagination = categoryRepository.findAll(
                PageRequest.of(pagination.getPage() - 1,
                        pagination.getLimit(), Sort.by(sortDirection,
                                "name")));

        paginationResponse.setContent(mapper.toDTO(categoryPagination.getContent()));

        paginationResponse.setPageNumber(pagination.getPage());
        paginationResponse.setPageSize(pagination.getLimit());

        paginationResponse.setTotalElements(categoryPagination.getTotalElements());
        paginationResponse.setTotalPages(categoryPagination.getTotalPages());
        paginationResponse.setNumberOfElements(categoryPagination.getNumberOfElements());
        paginationResponse.setFirst(categoryPagination.isFirst());
        paginationResponse.setLast(categoryPagination.isLast());

        return paginationResponse;
    }
}
