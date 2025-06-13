package com.shopkart.authorization.oauth2.security.services;

import com.shopkart.authorization.oauth2.security.records.ClientRegistrationRequestRecord;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

public interface IClientRegistrationService {
    void createClient(ClientRegistrationRequestRecord requestRecord);
    RegisteredClient findById(String id);
    RegisteredClient findByClientId(String clientId);
}
