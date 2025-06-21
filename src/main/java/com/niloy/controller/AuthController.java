package com.niloy.controller;

import com.niloy.config.JwtProvider;
import com.niloy.models.User;
import com.niloy.repository.UserRepository;
import com.niloy.request.LoginRequest;
import com.niloy.response.AuthRespose;
import com.niloy.service.CustomerUserDetailsService;
import com.niloy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    CustomerUserDetailsService customerUserDetailsService;


    @PostMapping("/signup")
    public AuthRespose createUser(@RequestBody User user) {
        User isExist = userRepository.findByEmail(user.getEmail());

        if (isExist != null) {
            throw new RuntimeException("User already exists with email: " + user.getEmail());
        }

        User newUser = new User();

        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword())); // Encode the password
        newUser.setGender(user.getGender());

        User savedUser = userRepository.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());

        String token = JwtProvider.generateToken(authentication); // Assuming you have a method to generate JWT token

        AuthRespose res = new AuthRespose(token, "Registration Succesa");// Assuming you have a method to generate JWT token
        return res;
    }

@PostMapping("/signin")
    public AuthRespose signin(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        String token = JwtProvider.generateToken(authentication); // Assuming you have a method to generate JWT token

        AuthRespose res = new AuthRespose(token, "Signin Succesa");// Assuming you have a method to generate JWT token
        return res;
    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = customerUserDetailsService.loadUserByUsername(email);

        if (userDetails == null) {
            throw new BadCredentialsException("User not found with email: " + email);
        }
        if(!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password for user: " + email);
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

    }
}
