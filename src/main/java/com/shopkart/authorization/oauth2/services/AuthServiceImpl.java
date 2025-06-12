package com.shopkart.authorization.oauth2.services;

import com.shopkart.authorization.oauth2.dtos.SignUpResponseRecord;
import com.shopkart.authorization.oauth2.exceptions.UserAlreadyExistException;
import com.shopkart.authorization.oauth2.models.User;
import com.shopkart.authorization.oauth2.repositories.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@Primary
public class AuthServiceImpl implements IAuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    public AuthServiceImpl(PasswordEncoder passwordEncoder,UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public SignUpResponseRecord signup(String email, String password) {
        boolean isUserExist=userRepository.existsByEmailAndIsDeletedIsFalse(email);
        if(isUserExist)
            throw new UserAlreadyExistException("User is already exist with email: "+email);
        User user = new User();
        user.setEmail(email);
        user.setPasswordSalt(passwordEncoder.encode(password));
        user=userRepository.save(user);
        return new SignUpResponseRecord(User.toUserRecord(user));
    }
}
