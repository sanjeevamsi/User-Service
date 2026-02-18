package com.example.User.controller;

import com.example.User.dto.UserDTO;
import com.example.User.service.JwtService;
import com.example.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-service")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody UserDTO userDto) {
        userDto.setPassword(encoder.encode(userDto.getPassword()));
        userService.saveUser(userDto);
        return ResponseEntity.ok("User registered successfully!");
    }
}
