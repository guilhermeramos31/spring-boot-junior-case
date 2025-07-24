package com.guilhermeramos31.springbootjuniorcase.configuration;

import com.guilhermeramos31.springbootjuniorcase.model.book.Book;
import com.guilhermeramos31.springbootjuniorcase.model.book.dto.BookRequestDTO;
import com.guilhermeramos31.springbootjuniorcase.model.book.dto.BookResponseDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        var mapper = new ModelMapper();

        mapper.addMappings(new PropertyMap<Book, BookResponseDTO>() {
            @Override
            protected void configure() {
                map().setAuthor(source.getAuthor().getId());
                map().setCategory(source.getCategory().getId());
            }
        });

        return mapper;
    }
}
