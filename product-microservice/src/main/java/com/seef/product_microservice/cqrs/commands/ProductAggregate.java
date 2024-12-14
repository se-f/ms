package com.seef.product_microservice.cqrs.commands;

import com.seef.product_microservice.cqrs.events.ProductCreatedEvent;
import com.seef.product_microservice.entities.Product;
import com.seef.product_microservice.repositories.ProductRepository;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;

@Aggregate
public class ProductAggregate {

    @AggregateIdentifier
    private String id;
    private String name;
    private String description;
    private double price;

    private final ProductRepository productRepository;

    @Autowired
    public ProductAggregate(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @CommandHandler
    public ProductAggregate(CreateProductCommand command, ProductRepository productRepository) {
        this.productRepository = productRepository;
        // Publish the ProductCreatedEvent
        AggregateLifecycle.apply(new ProductCreatedEvent(
                command.getId(),
                command.getName(),
                command.getDescription(),
                command.getPrice()
        ));
    }



    @EventSourcingHandler
    public void on(ProductCreatedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.description = event.getDescription();
        this.price = event.getPrice();

        productRepository.save(new Product(event.getId(), event.getName(), event.getDescription(), event.getPrice()));
    }
}
