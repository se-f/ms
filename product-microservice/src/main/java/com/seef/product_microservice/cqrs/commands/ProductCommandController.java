package com.seef.product_microservice.cqrs.commands;

import com.seef.product_microservice.entities.Product;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/products-cqrs")
public class ProductCommandController {

    @Autowired
    private CommandGateway commandGateway;

    @PostMapping
    public String createProduct(@RequestBody Product product) {
        String id = UUID.randomUUID().toString(); // Generate a unique ID

        CreateProductCommand command = new CreateProductCommand(
                id, product.getName(), product.getDescription(), product.getPrice());
        commandGateway.sendAndWait(command);
        return id;
    }


}
