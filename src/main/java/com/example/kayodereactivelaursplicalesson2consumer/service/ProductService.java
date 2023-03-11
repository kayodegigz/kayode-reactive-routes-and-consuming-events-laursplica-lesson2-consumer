package com.example.kayodereactivelaursplicalesson2consumer.service;

import com.example.kayodereactivelaursplicalesson2consumer.model.Product;
import com.example.kayodereactivelaursplicalesson2consumer.proxy.ProductProxy;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * Created by Kayode.Ogunrinde on 3/11/2023.
 */


// this collects the data from wherever it is, in this case from the proxy
@Service
public class ProductService {
    public ProductProxy productProxy;

    public ProductService(ProductProxy productProxy) {
        this.productProxy = productProxy;
    }

    public Flux<Product> getAll() {
        return productProxy.getAll();
    }
}
