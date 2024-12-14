package com.seef.product_microservice.cqrs.queries;

import com.seef.product_microservice.entities.Product;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/products-cqrs")
public class ProductQueryController {

    private final QueryGateway queryGateway;

    public ProductQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/{id}")
    public CompletableFuture<Product> getProductById(@PathVariable String id) {
        return queryGateway.query(new GetProductByIdQuery(id), Product.class);
    }
}

