package com.example.kayodereactivelaursplicalesson2consumer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Created by Kayode.Ogunrinde on 3/11/2023.
 */

@Configuration
public class WebClientConfig {

    @Value("${products.service.base.url}")
    private String baseUrl;

    @Bean
    public WebClient webClient() {
        return WebClient
                .builder()
                .baseUrl(baseUrl)
                .build();
    }
}
