package com.guilhermeramos31.springbootjuniorcase.service.author.interfaces;

import com.guilhermeramos31.springbootjuniorcase.model.author.dto.AuthorRequestDTO;
import com.guilhermeramos31.springbootjuniorcase.model.author.dto.AuthorResponseDTO;

import java.util.List;

public interface AuthorService {
    AuthorResponseDTO save(AuthorRequestDTO authorRequestDTO);
    AuthorResponseDTO findById(Long id);
    AuthorResponseDTO update(Long id, AuthorRequestDTO authorRequestDTO);
    void deleteById(Long id);
    List<AuthorResponseDTO> findAll(int page, int limit, String direction);
}
