package com.anuj.socialMediaApp.service;

import com.anuj.socialMediaApp.entity.UserEntity;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    ResponseEntity<List<UserEntity>> getAllUsers();

    ResponseEntity<UserEntity> saveUser(String name, LocalDate dateOfBirth);

    ResponseEntity<UserEntity> getUser(Integer id);

    ResponseEntity<Boolean> deleteUser(Integer id);
}
