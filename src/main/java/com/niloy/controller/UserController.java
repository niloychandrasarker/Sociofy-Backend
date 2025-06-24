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


    @GetMapping("/api/users")
    public List<User> getUser() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @GetMapping("/api/users/{userid}")
    public User getUserById(@PathVariable("userid") Integer id) {
        return userService.findUserById(id);
    }

    @GetMapping("/api/users/email/{email}")
    public User findUserByEmail(@PathVariable("email") String email) {

        return userService.findUserByEmail(email);
    }


    @PutMapping("/api/users")
    public User updateUser( @RequestHeader("Authorization") String token, @RequestBody User user) throws Exception {

       User reqUser = userService.findUserByJwt(token);
       User updatedUser = userService.updateUser(user, reqUser.getId());
       return updatedUser;
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

    @PutMapping("/api/users/follow/{userId2}")
    public User followUserHandler(@RequestHeader("Authorization") String token, @PathVariable("userId2") Integer userId2) throws Exception {
        User reqUser = userService.findUserByJwt(token);
        return userService.followUser(reqUser.getId(), userId2);
    }

    @GetMapping("/api/users/search")
    public List<User> searchUsers(@RequestParam("query") String query) {
        List<User> users = userService.searchUsers(query);
        return users;
    }

    @GetMapping("/api/users/profile")
    public  User getUserFromToken(@RequestHeader("Authorization") String token) {

        User user = userService.findUserByJwt(token);
        user.setPassword(null);
        return user;


    }
}