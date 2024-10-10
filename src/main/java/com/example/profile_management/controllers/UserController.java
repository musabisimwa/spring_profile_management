package com.example.profile_management.controllers;

import com.example.profile_management.entity.User;
import com.example.profile_management.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/user")
    public Long createUser(@Valid  @RequestBody User user) {

        return userService.saveUser(user);
    }

    @GetMapping("/api/users")
    public List<User> getAllUsers() {
        return userService.fetchAllUsers();
    }

    @GetMapping("/api/user/{id}")
    public User getOneUser(@PathVariable("id") Long id){
        //
        return userService.fetch(id);
    }
    @PutMapping("/api/user/{id}")
    public User update(@Valid @RequestBody User user, @PathVariable("id") Long id){
        return  userService.updateUser(user,id);
    }
    @DeleteMapping("/api/user/{id}")
    public Long deleteUser(@PathVariable("id")Long id){
        userService.deleteUser(id);
        return 1L;
    }
}