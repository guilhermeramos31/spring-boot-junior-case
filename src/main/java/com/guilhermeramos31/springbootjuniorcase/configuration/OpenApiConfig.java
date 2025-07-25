package com.guilhermeramos31.springbootjuniorcase.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiConfig {
    private final OpenAPI openAPI = new OpenAPI();

    @Bean
    public OpenAPI customOpenAPI() {
        return  openAPI
                .info(customInfo());
    }

    private Info customInfo() {
        var info = new Info();
        info.title("Junior Case");
        info.version("0.0.1");
        return info;
    }
}