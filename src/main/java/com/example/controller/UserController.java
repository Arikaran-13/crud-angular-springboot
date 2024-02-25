package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User>getUserById(@PathVariable("userId")int id){
        return userService.getUserById(id);
    }

    @PostMapping("/register") //demo app
    public ResponseEntity<User> createUser(@RequestBody User user)
    {
        return userService.registerUser(user);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>>getAllUser(){
        return userService.getAllUser();
    }
    }
