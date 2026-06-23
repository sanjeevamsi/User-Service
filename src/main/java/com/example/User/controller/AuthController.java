package com.example.User.controller;

import com.example.User.dto.UserDTO;
import com.example.User.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    //signUp
    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody UserDTO userDto) {
        authService.saveUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    //login
    @PostMapping("/login")
    public ResponseEntity<String> userLogin(@RequestBody UserDTO userDto) {
        try {
            String token = authService.verifyUser(userDto);
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
