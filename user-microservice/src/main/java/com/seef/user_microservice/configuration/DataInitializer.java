package com.seef.user_microservice.configuration;

import com.seef.user_microservice.entities.User;
import com.seef.user_microservice.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {


    private final UserService userService;
    public DataInitializer(UserService userService) {
        this.userService = userService;
    }

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            userService.addUser(new User(1, "John Doe", "john.doe@example.com", "password123"));
            userService.addUser(new User(2, "Jane Smith", "jane.smith@example.com", "securepassword"));
        };
    }
}
