package com.seef.product_microservice.cqrs.queries;

public class GetProductByIdQuery {

    private final String id;

    // Constructeur
    public GetProductByIdQuery(String id) {
        this.id = id;
    }

    // Getter pour l'id
    public String getId() {
        return id;
    }
}
