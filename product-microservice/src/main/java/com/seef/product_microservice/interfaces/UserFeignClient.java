package com.seef.product_microservice.interfaces;

import com.seef.product_microservice.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "user-microservice", url = "http://localhost:8081")
public interface UserFeignClient {

    @GetMapping("/users")
    List<UserDTO> getUsers();

}
