package com.spring.CineSense.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.CineSense.Model.Role;
import com.spring.CineSense.Model.User;
import com.spring.CineSense.Repository.RoleRepository;
import com.spring.CineSense.Repository.UserRepository;

@Service
public class SignUpService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public String signup(String fullName, String email, String password, String confirmPassword) {

        // Validation
        if (email == null || email.trim().isEmpty()) return "Email is required";
        if (password == null || password.trim().isEmpty()) return "Password is required";
        if (confirmPassword == null || confirmPassword.trim().isEmpty()) return "Confirm password is required";
        if (!password.equals(confirmPassword)) return "Passwords do not match";

        // Check existing user
        if (userRepository.findByEmail(email) != null) {
            return "Email already in use";
        }

        // Fetch role USER
        Role defaultRole = roleRepository.findByRoleName("USER");

        if (defaultRole == null) {
            return "Default role not found. Please contact admin.";
        }

        // Create new user
        User user = new User();
        user.setFullName(fullName);
        user.setEmail(email);

        user.setPasswordHash(password);

        user.setCreatedAt(LocalDateTime.now());
        user.setRole(defaultRole);

        userRepository.save(user);

        return null; // means success
    }
    
    public void updateUser(User user) {
        userRepository.save(user);
    }
    
}
