package com.example.User.service;


import com.example.User.dto.UserDTO;
import com.example.User.model.User;
import com.example.User.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    private UserRepo userRepo;

    public void saveUser(UserDTO userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setRole(userDto.getRole());
        //the created and updated fields will be automatically set by the database, so we don't need to set them here
        userRepo.save(user);
    }
}
