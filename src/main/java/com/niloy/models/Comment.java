package com.niloy.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String content;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<User> likeed = new ArrayList<>();

    private LocalDateTime createdAt;

    public Comment() {
    }

    public Comment(Integer id, String content, User user, List<User> likeed, LocalDateTime createdAt) {
        super();
        this.id = id;
        this.content = content;
        this.user = user;
        this.likeed = likeed;
        this.createdAt = createdAt;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getLikeed() {
        return likeed;
    }

    public void setLikeed(List<User> likeed) {
        this.likeed = likeed;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

