package com.example.kayodereactivelaursplicalesson2consumer.config;

import com.example.kayodereactivelaursplicalesson2consumer.handler.ProductHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * Created by Kayode.Ogunrinde on 3/11/2023.
 */

// the routes config is for the internal application
// sort of like a controller to handle incoming requests
@Configuration
public class RoutesConfig {

    private ProductHandler productHandler;

    public RoutesConfig(ProductHandler productHandler) {
        this.productHandler = productHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> router(ProductHandler productHandler) {
        return route().GET("/all", productHandler::getAll)
                .build();
    }
}
