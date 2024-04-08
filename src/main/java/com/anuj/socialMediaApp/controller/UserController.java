package com.anuj.socialMediaApp.controller;

import com.anuj.socialMediaApp.entity.UserEntity;
import com.anuj.socialMediaApp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/users", produces = {"application/json", "application/xml"})
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(path = "/users", produces = {"application/json", "application/xml"})
    public ResponseEntity<UserEntity> saveUser(@Valid @RequestBody UserEntity userEntity) {
        return userService.saveUser(userEntity.getName(), userEntity.getDateOfBirth());
    }

    @GetMapping(path = "/users/{id}", produces = {"application/json", "application/xml"})
    public ResponseEntity<UserEntity> getUser(@PathVariable Integer id) {
        return userService.getUser(id);
    }

    @DeleteMapping(path = "/users/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }
}
