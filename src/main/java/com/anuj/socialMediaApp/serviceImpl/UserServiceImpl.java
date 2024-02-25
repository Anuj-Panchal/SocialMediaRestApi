package com.anuj.socialMediaApp.serviceImpl;

import com.anuj.socialMediaApp.entity.UserEntity;
import com.anuj.socialMediaApp.repository.UserRepository;
import com.anuj.socialMediaApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        List<UserEntity> userEntityList = userRepository.findAll();
        if(userEntityList == null || userEntityList.isEmpty())
            return ResponseEntity.noContent().build();
        return new ResponseEntity<>(userEntityList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserEntity> saveUser(String name, LocalDate dateOfBirth) {
        try {
            UserEntity userEntity = new UserEntity(name, dateOfBirth);

            userEntity = userRepository.save(userEntity);
            URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequestUri()
                    .path("/{id}")
                    .buildAndExpand(userEntity.getId())
                    .toUri();

            return ResponseEntity.created(uri).build();
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<UserEntity> getUser(Integer id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);

        if(!userEntity.isPresent())
            return ResponseEntity.noContent().build();
        return new ResponseEntity<>(userEntity.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> deleteUser(Integer id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);

        if(!userEntity.isPresent())
            return new ResponseEntity<>(false, HttpStatus.NO_CONTENT);
        userRepository.delete(userEntity.get());
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
