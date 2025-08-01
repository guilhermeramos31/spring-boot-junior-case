package com.guilhermeramos31.springbootjuniorcase.services.category;

import com.guilhermeramos31.springbootjuniorcase.model.book.dto.BookResponseDTO;
import com.guilhermeramos31.springbootjuniorcase.model.book.mapper.BookMapper;
import com.guilhermeramos31.springbootjuniorcase.model.category.Category;
import com.guilhermeramos31.springbootjuniorcase.model.category.dto.CategoryPaginationRequestDTO;
import com.guilhermeramos31.springbootjuniorcase.model.category.dto.CategoryPaginationResponseDTO;
import com.guilhermeramos31.springbootjuniorcase.model.category.dto.CategoryRequestDTO;
import com.guilhermeramos31.springbootjuniorcase.model.category.dto.CategoryResponseDTO;
import com.guilhermeramos31.springbootjuniorcase.model.category.mapper.CategoryMapper;
import com.guilhermeramos31.springbootjuniorcase.repositories.interfaces.CategoryRepository;
import com.guilhermeramos31.springbootjuniorcase.services.category.interfaces.CategoryService;
import com.guilhermeramos31.springbootjuniorcase.utils.PaginationUtil;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper mapper;
    private final BookMapper bookMapper;

    private Category getCategoryByName(String name) {
        return categoryRepository.findByName(name).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public CategoryResponseDTO create(CategoryRequestDTO categoryRequestDTO) {
        var category = categoryRepository.findByName(categoryRequestDTO.getName());
        if (category.isPresent()) {
            CategoryServiceImpl.log.atWarn().log("Category with name " + categoryRequestDTO.getName() + " already exists");
            return mapper.toDTO(this.getCategoryByName(categoryRequestDTO.getName()));
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

        return PaginationUtil.buildPaginationResponse(
                categoryPagination,
                paginationResponse,
                pagination,
                mapper::toDTO
        );
    }

    @Override
    public Category getCategoryById(long id) {
        return categoryRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Category not found"));
    }

    @Override
    public List<BookResponseDTO> findBookByCategoryId(long id) {
        var category = categoryRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Category not found"));

        return category.getBooks().stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList()) ;
    }
}
