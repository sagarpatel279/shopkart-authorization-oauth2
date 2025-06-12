package com.shopkart.authorization.oauth2.security.services;

import com.shopkart.authorization.oauth2.security.models.Client;

public interface IClientRegistrationService {
    void createClient(Client client);
    Client findById(String id);
    Client findByClientId(String clientId);
}
