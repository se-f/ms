package com.seef.product_microservice.services;

import com.seef.product_microservice.dto.UserDTO;
import com.seef.product_microservice.entities.Product;
import com.seef.product_microservice.interfaces.UserFeignClient;
import com.seef.product_microservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {


    private final ProductRepository productRepository;
    private final UserFeignClient userFeignClient;
    public ProductService(ProductRepository productRepository, UserFeignClient userFeignClient) {
        this.productRepository = productRepository;
        this.userFeignClient = userFeignClient;
    }


    // crud methods
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public Product getProductById(String id) {
        return productRepository.findById(id).get();
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<UserDTO> getUserForProduit() {
        return userFeignClient.getUsers();
    }

}
