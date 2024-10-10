package com.example.profile_management.services;

import com.example.profile_management.entity.User;

import java.util.List;

/*
* interface the user entity and define the crud methods
* */
public interface UserService {
    Long saveUser(User user); // we want to return the id every time we save a user record
    List<User> fetchAllUsers(); // SELECT * FROM users
    User updateUser(User user,Long id);
    User fetch(Long id); // get a single user
    void deleteUser(Long id); // delete a user by id

}
