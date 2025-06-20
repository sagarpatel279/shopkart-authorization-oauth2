package com.shopkart.authorization.oauth2.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopkart.authorization.oauth2.clients.KafkaProducerClient;
import com.shopkart.authorization.oauth2.clients.dtos.EmailServicePayLoadDto;
import com.shopkart.authorization.oauth2.dtos.SignUpResponseRecord;
import com.shopkart.authorization.oauth2.dtos.UserRecord;
import com.shopkart.authorization.oauth2.exceptions.ObjectToJSONException;
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
    private KafkaProducerClient kafkaProducerClient;
    private ObjectMapper objectMapper;
    public AuthServiceImpl(PasswordEncoder passwordEncoder,UserRepository userRepository, KafkaProducerClient kafkaProducerClient, ObjectMapper objectMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.kafkaProducerClient=kafkaProducerClient;
        this.objectMapper=objectMapper;
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
        UserRecord userRecord=User.toUserRecord(user);
        EmailServicePayLoadDto emailServicePayLoadDto = new EmailServicePayLoadDto();
        emailServicePayLoadDto.setTo(userRecord.email());
        emailServicePayLoadDto.setSubject("Welcome to Shop Kart");
        emailServicePayLoadDto.setBody("Thanks for creating an account on Shop Kart...");
        try {
            kafkaProducerClient.sendEmail("sendEmail", objectMapper.writeValueAsString(emailServicePayLoadDto));
        }catch (JsonProcessingException jpe){
            throw new ObjectToJSONException(jpe.getMessage());
        }
        return new SignUpResponseRecord(userRecord);
    }
}
