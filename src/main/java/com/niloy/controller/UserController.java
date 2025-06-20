package com.niloy.controller;

import com.niloy.models.User;
import com.niloy.repository.UserRepository;
import com.niloy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        User savedUser = userService.registerUser(user);
        return savedUser;
    }

    @GetMapping("/users")
    public List<User> getUser() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @GetMapping("/users/{userid}")
    public User getUserById(@PathVariable("userid") Integer id) {
        return userService.findUserById(id);
    }

    @GetMapping("/users/email/{email}")
    public User findUserByEmail(@PathVariable("email") String email) {
        return userService.findUserByEmail(email);
    }


    @PutMapping("/users/{userid}")
    public User updateUser(@RequestBody User user, @PathVariable("userid") Integer id) throws Exception {
        return userService.updateUser(user, id);
    }

//    @DeleteMapping("/users/{userid}")
//    public String deleteUser(@PathVariable("userid") Integer id)  {
//        Optional<User> user = userRepository.findById(id);
//        if (user.isEmpty()) {
//            throw new RuntimeException("User not found with id: " + id);
//        }
//        userRepository.deleteById(id);
//
//
//        return "User with id " + id + " deleted successfully";
//    }

    @PutMapping("/users/follow/{userId1}/{userId2}")
    public User followUserHandler(@PathVariable("userId1") Integer userId1, @PathVariable("userId2") Integer userId2) throws Exception {
        return userService.followUser(userId1, userId2);
    }

    @GetMapping("/users/search")
    public List<User> searchUsers(@RequestParam("query") String query) {
        List<User> users = userService.searchUsers(query);
        return users;
    }
}