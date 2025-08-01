package com.guilhermeramos31.springbootjuniorcase.services.web_scraping.interfaces;

import com.guilhermeramos31.springbootjuniorcase.model.book.dto.BookResponseDTO;

public interface WebScrapingService {
    BookResponseDTO extract(long id);
}
