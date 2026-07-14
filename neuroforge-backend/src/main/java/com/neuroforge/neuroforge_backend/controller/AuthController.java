package com.neuroforge.neuroforge_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.neuroforge.neuroforge_backend.dto.RegisterRequest;
import com.neuroforge.neuroforge_backend.entity.User;
import com.neuroforge.neuroforge_backend.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
public User register(@RequestBody RegisterRequest request) {

    User user = new User();

    user.setFirstName(request.getFirstName());
    user.setLastName(request.getLastName());
    user.setEmail(request.getEmail());
    user.setPassword(request.getPassword());

    return userService.registerUser(user);
}

}