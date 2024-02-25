package com.anuj.socialMediaApp.repository;

import com.anuj.socialMediaApp.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findById(Integer id);

    List<UserEntity> findAll();
}
