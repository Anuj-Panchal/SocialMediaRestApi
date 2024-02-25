package com.anuj.socialMediaApp.service;

import com.anuj.socialMediaApp.entity.PostEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {

    ResponseEntity<List<PostEntity>> getAllPosts(Integer id);

    ResponseEntity<PostEntity> createPost(Integer id, PostEntity postEntity);

    ResponseEntity<PostEntity> getPost(Integer id, Integer postId);
}
