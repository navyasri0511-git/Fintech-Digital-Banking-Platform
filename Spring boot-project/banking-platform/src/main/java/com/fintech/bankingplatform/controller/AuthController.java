package com.fintech.bankingplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fintech.bankingplatform.dto.LoginRequest;
import com.fintech.bankingplatform.dto.LoginResponse;
import com.fintech.bankingplatform.model.User;
import com.fintech.bankingplatform.repository.UserRepository;
import com.fintech.bankingplatform.security.JwtUtil;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = JwtUtil.generateToken(user.getEmail());

        return new LoginResponse(token, user.getId());
    }
}