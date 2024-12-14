package com.seef.product_microservice.configuration;

import com.seef.product_microservice.entities.Product;
import com.seef.product_microservice.repositories.ProductRepository;
import com.seef.product_microservice.services.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    private final ProductService productService;
    public DataInitializer(ProductService productService) {
        this.productService = productService;
    }

    @Bean
    CommandLineRunner initDatabase(ProductService productService) {
        return args -> {
            productService.addProduct(new Product("1", "Product 1", "Description of product 1", 100.0));
            productService.addProduct(new Product("2", "Product 2", "Description of product 2", 200.0));
        };
    }

}
