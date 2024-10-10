package com.example.profile_management.controllers;

import com.example.profile_management.entity.User;
import com.example.profile_management.records.UserRecord;
import com.example.profile_management.services.UserService;
import com.example.profile_management.utils.ControllerUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/users")
    public ResponseEntity<Long> createUser(@Valid  @RequestBody UserRecord user) {

         Long id =userService.saveUser(
                ControllerUtils.fromUserRecord(user)
        );
         return ResponseEntity.status(HttpStatus.CREATED)
                 .body(id);
    }

    @GetMapping("/api/users")
    public ResponseEntity<List<UserRecord>> getAllUsers() {
        List<UserRecord> userRecords= userService.fetchAllUsers()
                .stream()
                .map(ControllerUtils::toUserRecord)
                .toList();
        return ResponseEntity.ok(userRecords);
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<UserRecord> getOneUser( @PathVariable("id") Long id){
        //
        User user = userService.fetch(id);
        return ResponseEntity.ok(
               ControllerUtils.toUserRecord(user)
        );
    }
    @PutMapping("/api/users/{id}")
    public ResponseEntity<UserRecord> update(@Valid @RequestBody UserRecord user, @PathVariable("id") Long id){
        User updatedUser = userService.updateUser(
                ControllerUtils.fromUserRecord(user)
                ,id);
        return  ResponseEntity.ok(ControllerUtils.toUserRecord(updatedUser));
    }
    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<String> deleteUser( @PathVariable("id")Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}