package com.anuj.socialMediaApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private UserEntity user;

    public PostEntity(String content) {
        this.content = content;
    }

    public PostEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "PostEntity{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", user=" + user +
                '}';
    }
}
