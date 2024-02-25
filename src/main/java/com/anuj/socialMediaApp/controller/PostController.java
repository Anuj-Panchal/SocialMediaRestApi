package com.anuj.socialMediaApp.controller;

import com.anuj.socialMediaApp.entity.PostEntity;
import com.anuj.socialMediaApp.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping(path = "/users/{id}/posts")
    public ResponseEntity<PostEntity> createPost(@PathVariable Integer id, @Valid @RequestBody PostEntity postEntity) {
        return postService.createPost(id, postEntity);
    }

    @GetMapping(path = "/users/{id}/posts")
    public ResponseEntity<List<PostEntity>> getAllPosts(@PathVariable Integer id) {
        return postService.getAllPosts(id);
    }

    @GetMapping(path = "/users/{id}/posts/{postId}")
    public ResponseEntity<PostEntity> getAllPosts(@PathVariable Integer id, @PathVariable Integer postId) {
        return postService.getPost(id, postId);
    }
}
