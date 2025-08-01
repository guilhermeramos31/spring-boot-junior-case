package com.guilhermeramos31.springbootjuniorcase.controller;

import com.guilhermeramos31.springbootjuniorcase.model.book.dto.BookResponseDTO;
import com.guilhermeramos31.springbootjuniorcase.services.web_scraping.interfaces.WebScrapingService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("web-scraping")
public class WebScrapingController {
    private final WebScrapingService webScrapingService;

    @PostMapping("/{id}")
    public ResponseEntity<BookResponseDTO> scrape(@PathVariable @Valid @NotNull long id) {
        return ResponseEntity.ok(webScrapingService.extract(id));
    }
}
