package com.scm.controllers;

import com.scm.controllers.responses.AuthResponse;
import com.scm.entities.User;
import com.scm.helpers.RandomImageSelector;
import com.scm.repsitories.UserRepo;
import com.scm.utils.JwtUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;
    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil,
            UserRepo userRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody Map<String, String> request) {
        logger.info("Login request received: {}", request);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.get("email"), request.get("password")));

            String token = jwtUtil.generateToken(request.get("email"));
            System.out.println("Generated Token: " + token);
            User user = userRepository.findByEmail(request.get("email")).orElse(null);
            AuthResponse authResponse = new AuthResponse(token, user);
            return ResponseEntity.ok(authResponse);
        } catch (Exception e) {
            logger.error("Authentication failed: {}" , e.getMessage());
            AuthResponse authResponse = new AuthResponse(null, null);
            authResponse.setError("Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authResponse);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        logger.info("Register request received: {}", user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setProfilePic(RandomImageSelector.getRandomImageUrl());
        userRepository.save(user);
        return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
    }
}