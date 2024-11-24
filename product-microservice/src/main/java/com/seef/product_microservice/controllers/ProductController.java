package com.seef.product_microservice.controllers;

import com.seef.product_microservice.dto.UserDTO;
import com.seef.product_microservice.entities.Product;
import com.seef.product_microservice.services.ProductService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
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
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @RateLimiter(name = "myRateLimiter", fallbackMethod = "fallback")
    @GetMapping("/users-for-produits")
    @CircuitBreaker(name = "product-microservice", fallbackMethod = "fallback")
    @Retry(name = "myRetry", fallbackMethod = "fallback")
    public List<UserDTO> getUserForProduit() {
        return productService.getUserForProduit();
    }

    public List<UserDTO> fallback(Throwable e){
        System.err.println("Erreur lors de l'appel à getUserForProduit : " + e.getMessage());
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

}
