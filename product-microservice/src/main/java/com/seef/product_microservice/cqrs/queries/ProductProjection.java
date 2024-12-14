package com.seef.product_microservice.cqrs.queries;

import com.seef.product_microservice.entities.Product;
import com.seef.product_microservice.repositories.ProductRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductProjection {

    @Autowired
    private ProductRepository productRepository;

    @QueryHandler
    public Product handle(GetProductByIdQuery query) {
        return productRepository.findById(query.getId()).orElse(null);
    }
}
