package com.seef.product_microservice.controllers;

import com.seef.product_microservice.dto.UserDTO;
import com.seef.product_microservice.entities.Product;
import com.seef.product_microservice.services.ProductService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produits")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
//    @Retry(name = "myRetry", fallbackMethod = "fallback")
    @RateLimiter(name = "myRateLimiter", fallbackMethod = "fallback")
//    @CircuitBreaker(name = "product-microservice", fallbackMethod = "fallback")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @RateLimiter(name = "myRateLimiter", fallbackMethod = "fallbackRateLimit")
    @GetMapping("/users-for-product")
    public List<UserDTO> getUserForProduit() {
        return productService.getUserForProduit();
    }

    public List<UserDTO> fallbackRateLimit(){
        System.out.println("Fallback method called");
        return List.of(new UserDTO(0, "User -1"));
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Integer id) {
        return productService.getProductById(id);
    }

    @PostMapping()
    public void addProduct(Product product) {
        productService.addProduct(product);
    }

    @PutMapping()
    public void updateProduct(Product product) {
        productService.updateProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
    }


    public List<Product> fallback(Exception e) {
        System.out.println("Fallback method called");
        return List.of(new Product(0, "Product -1", "-1", 0.0));
    }

}
