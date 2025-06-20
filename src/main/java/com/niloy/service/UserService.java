package com.niloy.service;

import com.niloy.models.User;

import java.util.List;

public interface UserService {
    public User registerUser(User user);
    public User findUserById(Integer userId);
    public User findUserByEmail(String email);
    public User followUser(Integer userId1, Integer userId2) throws Exception;
    public User updateUser(User user, Integer userId) throws Exception;
    public List<User>searchUsers(String query);
}
