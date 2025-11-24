package com.spring.CineSense.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.CineSense.Model.User;
import com.spring.CineSense.Repository.UserRepository;

@Service
public class LogInService {

    @Autowired
    private UserRepository userRepository;

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            return null;    // no user with email
        }

        // For now: normal text comparison
        // Later: we will use BCryptPasswordEncoder
        if (user.getPasswordHash().equals(password)) {
            return user;
        }

        return null;
    }
}
