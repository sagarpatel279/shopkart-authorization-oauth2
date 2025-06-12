package com.shopkart.authorization.oauth2.security.services;

import com.shopkart.authorization.oauth2.security.models.Client;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClientRegistrationService implements IClientRegistrationService{

    private PasswordEncoder passwordEncoder;

    public ClientRegistrationService(PasswordEncoder passwordEncoder){
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public void createClient(Client client) {

    }

    @Override
    public Client findById(String id) {
        return null;
    }

    @Override
    public Client findByClientId(String clientId) {
        return null;
    }
}
