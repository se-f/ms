package com.seef.user_microservice.services;

import com.seef.user_microservice.dto.ProduitDTO;
import com.seef.user_microservice.entities.User;
import com.seef.user_microservice.interfaces.ProduitFeignClient;
import com.seef.user_microservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ProduitFeignClient produitFeignClient;
    public UserService(UserRepository userRepository, ProduitFeignClient produitFeignClient) {
        this.userRepository = userRepository;
        this.produitFeignClient = produitFeignClient;
    }

    // crud methods
    public User addUser(User user) {
        System.out.println("Received user: " + user);

        if (user.getName() == null || user.getEmail() == null || user.getPassword() == null) {
            throw new IllegalArgumentException("Name, email, and password must not be null");
        }
        userRepository.save(user);
        return user;
    }

    public User getUserById(int id) {
        return userRepository.findById(id).get();
    }

    public List<User> getAllUsers(){
        System.out.println("Getting all users");
        return userRepository.findAll();
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    // Update user
    public User updateUser(Integer id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        user.setName(userDetails.getName());
        return userRepository.save(user);
    }

    public List<ProduitDTO> getProduitForUser() {
        return produitFeignClient.getProduitsForUser();
    }
}
