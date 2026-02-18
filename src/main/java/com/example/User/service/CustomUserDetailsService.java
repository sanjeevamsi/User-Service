package com.example.User.service;


import com.example.User.model.User;
import com.example.User.model.UserPrincipal;
import com.example.User.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //with the user name entered lets fetch the user from the database
        User user = userRepo.findByUsername(username);

        //once we get the user details we have to return that user details

        if(user == null) {
            throw new RuntimeException("user not found " + username);
        }

        //User Principal is the class that implements UserDetails and we have to return that user details object
        //the userdetails object have to be like this
        //new UserDetails(user.getUsername(), user.getPassword(), user.getAuthorities());
        return new UserPrincipal(user);
    }
}
