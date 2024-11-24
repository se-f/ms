package com.seef.product_microservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private int id;
    private String name;

    public UserDTO(int i, String s) {
        this.id = i;
        this.name = s;
    }

    public UserDTO() {
    }
}

