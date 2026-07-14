package com.neuroforge.neuroforge_backend.service.impl;

import org.springframework.stereotype.Service;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.neuroforge.neuroforge_backend.entity.Role;
import com.neuroforge.neuroforge_backend.entity.User;
import com.neuroforge.neuroforge_backend.repository.RoleRepository;
import com.neuroforge.neuroforge_backend.repository.UserRepository;
import com.neuroforge.neuroforge_backend.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

public UserServiceImpl(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {

    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.passwordEncoder = passwordEncoder;
}

    @Override
    public User registerUser(User user) {

        // Check duplicate email
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        // Get default role
        Role role = roleRepository.findByName("DEVELOPER")
                .orElseThrow(() -> new RuntimeException("Default role not found"));

        // Assign role
        user.setRole(role);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save user
        return userRepository.save(user);
    }
}