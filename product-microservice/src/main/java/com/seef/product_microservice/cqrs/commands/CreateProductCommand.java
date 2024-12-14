package com.seef.product_microservice.cqrs.commands;

import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
public class CreateProductCommand {

    @TargetAggregateIdentifier
    private final String id;
    private final String name;
    private final String description;
    private final double price;

    public CreateProductCommand(String id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
