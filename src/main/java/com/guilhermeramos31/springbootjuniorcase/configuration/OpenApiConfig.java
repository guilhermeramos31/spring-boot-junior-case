package com.guilhermeramos31.springbootjuniorcase.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;


@Configuration
public class OpenApiConfig {
    private final OpenAPI openAPI = new OpenAPI();

    @Bean
    public OpenAPI customOpenAPI() {
        return openAPI
                .info(customInfo())
                .tags(customTags());
    }

    private Info customInfo() {
        var info = new Info();
        info.title("Junior Case");
        info.version("0.0.1");
        return info;
    }

    private List<Tag> customTags() {
        return Arrays.asList(
                new Tag().name("Author").description("Operations related to authors"),
                new Tag().name("Category").description("Operations related to categories"),
                new Tag().name("Book").description("Operations related to books")
        );
    }
}