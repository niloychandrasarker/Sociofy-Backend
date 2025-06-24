package com.niloy.service;

import com.niloy.config.JwtProvider;
import com.niloy.models.User;
import com.niloy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplimentation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        User newUser = new User();
        newUser.setId(user.getId());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setGender(user.getGender());

        User savedUser = userRepository.save(newUser);
        return savedUser;
    }

    @Override
    public User findUserById(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
    }

    @Override
    public User findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found with email: " + email);
        }
        return user;
    }

    @Override
    public User followUser(Integer reqUserId1, Integer userId2) throws Exception {
        User reqUser = findUserById(reqUserId1);
        User user2 = findUserById(userId2);

        if (reqUser.getFollowing().contains(userId2)) {
            throw new Exception("User " + reqUserId1 + " is already following user " + userId2);
        }

        reqUser.getFollowing().add(user2.getId());
        user2.getFollowers().add(reqUser.getId());

        userRepository.save(reqUser);
        userRepository.save(user2);

        return reqUser;
    }

    @Override
    public User updateUser(User user, Integer id) throws Exception {
        Optional<User> user1 = userRepository.findById(id);

        if (user1.isEmpty()) {
            throw new RuntimeException("User not found with id: " + id);
        } else {
            User existingUser = user1.get();

            if (user.getFirstName() != null) {
                existingUser.setFirstName(user.getFirstName());
            }

            if (user.getLastName() != null) {
                existingUser.setLastName(user.getLastName());
            }

            if (user.getEmail() != null) {
                existingUser.setEmail(user.getEmail());
            }

            if (user.getPassword() != null) {
                existingUser.setPassword(user.getPassword());
            }
            if (user.getGender() != null) {
                existingUser.setGender(user.getGender());
            }

            return userRepository.save(existingUser);
        }
    }

    @Override
    public List<User> searchUsers(String query) {
        if (query == null || query.isEmpty()) {
            return userRepository.findAll();
        }
        return userRepository.searchUser(query);
    }

    @Override
    public User findUserByJwt(String jwt) {
        String email = JwtProvider.getEmailFromJwtToken(jwt);

        if (email == null || email.isEmpty()) {
            throw new RuntimeException("Invalid JWT token: Unable to extract email.");
        }

        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found with email: " + email);
        }

        return user;
    }
}
