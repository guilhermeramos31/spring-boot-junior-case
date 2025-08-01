package com.guilhermeramos31.springbootjuniorcase.configuration;

import org.htmlunit.WebClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebClientConfig {

    @Value("${web-scraping.timeout}")
    private Integer webScrapingTimeout;

    @Bean
    public WebClient webClient() {
        var webClient = new WebClient();
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setTimeout(webScrapingTimeout);
        return webClient;
    }
}
