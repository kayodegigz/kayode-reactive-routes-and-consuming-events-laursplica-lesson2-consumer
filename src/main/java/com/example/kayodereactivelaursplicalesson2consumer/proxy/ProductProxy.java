package com.example.kayodereactivelaursplicalesson2consumer.proxy;

import com.example.kayodereactivelaursplicalesson2consumer.model.Product;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
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

        // exception handling, once an error happens, replace the current flux with a new one
        Product p = new Product();
        p.setName("Default Error");

        /**
        the first onErrorResume that is commented out will do the same thing for every error
        the second onErrorResponse implements a function for each exception.
        the third and 4th onErrorResume will take a predicate as first argument, the
        - predicate will return either true or false, depending on if the condition is met or not
        the first onErrorResume is not advised cos it is too generic
         */

        return webClient
                .get()
                .uri("/product")
                .exchangeToFlux(response -> response.bodyToFlux(Product.class))
//                .onErrorResume(e -> Flux.just(p))
//                .onErrorResume(WebClientRequestException.class, e -> Flux.just(p))
//                .onErrorResume(e -> e.getMessage() == null, e -> Flux.just(p))
                .onErrorResume(e -> e instanceof WebClientRequestException, e -> Flux.just(p))
//                .onErrorReturn(p)
//                .onErrorReturn(WebClientRequestException.class, p)
                .onErrorReturn(e -> e.getMessage() == null, p)
                .onErrorContinue((e, item) -> System.out.println(e.getMessage()))
                .onErrorContinue(RuntimeException.class, (e, item) -> System.out.println(e.getMessage()))
                .onErrorContinue(e -> e.getMessage() == null, (e, item)
                        -> System.out.println(e.getMessage()))
                .retry(3);

        /**
         what onErrorReturn() does is it simplifies the syntax, all that is needed is to
        - pass it a value and it automatically creates a flux under the hood for it.
        The disadvantage is that you don't get access to the exception object and you will
        probably need it in real world scenarios. It also has 3 overloaded implementations
        Just like onErrorResume.
         */

        /**
         * The onErrorContinue() method carries out a certain operation once an error
         * is encountered, then skips the element and moves to the next one
         * It has similar method overloading implementations as onErrorResume...
         */

        /**
         * The retry() method specifies how many times to retry when there's a failure.
         */
    }
}
