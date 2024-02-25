package com.anuj.socialMediaApp.repository;

import com.anuj.socialMediaApp.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Integer> {
}
