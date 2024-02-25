package com.example.service;

import com.example.entity.UserEntity;
import com.example.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    ResponseEntity<User> registerUser(User userDto);
    ResponseEntity<User> getUserById(int userId);

    ResponseEntity<List<User>>getAllUser();
}
