package com.shopkart.authorization.oauth2.security.services;

import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

public interface IClientRegistrationService {
    void createClient(RegisteredClient registeredClient);
    RegisteredClient findById(String id);
    RegisteredClient findByClientId(String clientId);
}
