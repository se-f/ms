package com.seef.product_microservice.cqrs.events;

import lombok.Getter;

@Getter
public class ProductCreatedEvent {
    private final String id;
    private final String name;
    private final String description;
    private final double price;

    public ProductCreatedEvent(String id, String name, String description, double price)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

}