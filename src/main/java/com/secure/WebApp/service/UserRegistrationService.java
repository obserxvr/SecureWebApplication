package com.secure.WebApp.service;

import com.secure.WebApp.model.User;
import com.secure.WebApp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserRegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registers a new user. Returns true on success, false if username already exists.
     */
    public boolean register(String username, String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            return false;
        }

        User user = new User(username, passwordEncoder.encode(password), "USER");
        userRepository.save(user);
        return true;
    }
}
