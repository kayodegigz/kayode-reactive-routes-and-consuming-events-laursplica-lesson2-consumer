package com.example.kayodereactivelaursplicalesson2consumer.proxy;

import com.example.kayodereactivelaursplicalesson2consumer.model.Product;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

/**
 * Created by Kayode.Ogunrinde on 3/11/2023.
 */

// responsible for calling external endpoint
@Component
public class ProductProxy {
    private final WebClient webClient;

    public ProductProxy(WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<Product> getAll() {
        return webClient
                .get()
                .uri("/product")
                .exchangeToFlux(response -> response.bodyToFlux(Product.class));
    }
}
