package com.niloy.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String gender;
    @ElementCollection
    private List<Integer> followers=new ArrayList<>();

    @ElementCollection
    private List<Integer> following= new ArrayList<>();

    @ManyToMany
//    @JoinTable(
//            name = "user_saved_posts",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "post_id")
//    )
    @JsonIgnore
    private List<Post> savedPosts = new ArrayList<>();

    public User() {
    }

    public User(Integer id, String firstName, String lastName, String email, String password, String gender, List<Integer> followers, List<Integer> following, List<Post> savedPosts) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.followers = followers;
        this.following = following;
        this.savedPosts = savedPosts;
    }

// Getters and Setters

    public List<Integer> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Integer> followers) {
        this.followers = followers;
    }

    public List<Integer> getFollowing() {
        return following;
    }

    public void setFollowing(List<Integer> following) {
        this.following = following;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public List<Post> getSavedPosts() {
        return savedPosts;
    }

    public void setSavedPosts(List<Post> savedPosts) {
        this.savedPosts = savedPosts;
    }
}
