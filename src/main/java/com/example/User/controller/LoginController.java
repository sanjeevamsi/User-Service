package com.example.User.controller;

import com.example.User.dto.UserDTO;
import com.example.User.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-service")
public class LoginController {

    @Autowired
    private JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
        if(!authentication.isAuthenticated()) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
        String username = authentication.getName();
        String token = jwtService.generateToken(username);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/test")
    public String testExample() {
        return "JWT is working!";
    }
}

