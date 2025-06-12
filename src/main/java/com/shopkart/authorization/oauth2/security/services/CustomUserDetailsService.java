package com.shopkart.authorization.oauth2.security.services;

import com.shopkart.authorization.oauth2.models.User;
import com.shopkart.authorization.oauth2.repositories.UserRepository;
import com.shopkart.authorization.oauth2.security.models.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional=userRepository.findByEmailAndIsDeletedIsFalse(username);
        if(userOptional.isEmpty()){
            throw new UsernameNotFoundException("User could not found with username: "+username);
        }
        return new CustomUserDetails(userOptional.get());
    }
}
