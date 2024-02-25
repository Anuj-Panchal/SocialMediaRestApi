package com.anuj.socialMediaApp.serviceImpl;

import com.anuj.socialMediaApp.entity.PostEntity;
import com.anuj.socialMediaApp.entity.UserEntity;
import com.anuj.socialMediaApp.repository.PostRepository;
import com.anuj.socialMediaApp.repository.UserRepository;
import com.anuj.socialMediaApp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public ResponseEntity<List<PostEntity>> getAllPosts(Integer id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if(!userEntity.isPresent())
            return ResponseEntity.noContent().build();
        List<PostEntity> postEntityList = userEntity.get().getPosts();
        if(postEntityList == null || postEntityList.isEmpty())
            return ResponseEntity.noContent().build();
        return new ResponseEntity<>(postEntityList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PostEntity> createPost(Integer id, PostEntity postEntity) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if(!userEntity.isPresent())
            return ResponseEntity.badRequest().build();
        postEntity.setUser(userEntity.get());
        postEntity = postRepository.save(postEntity);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{post_id}")
                .buildAndExpand(postEntity.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    public ResponseEntity<PostEntity> getPost(Integer id, Integer postId) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if(!userEntity.isPresent())
            return ResponseEntity.noContent().build();
        List<PostEntity> postEntityList = userEntity.get().getPosts();
        if(postEntityList == null || postEntityList.isEmpty())
            return ResponseEntity.noContent().build();
        for(PostEntity postEntity: postEntityList) {
            if(Objects.equals(postEntity.getId(), postId))
                return new ResponseEntity<>(postEntity, HttpStatus.OK);
        }
        return ResponseEntity.noContent().build();
    }
}
