package com.shopkart.authorization.oauth2.security.services;

import com.shopkart.authorization.oauth2.security.exceptions.ClientAlreadyExistException;
import com.shopkart.authorization.oauth2.security.records.ClientRegistrationRequestRecord;
import com.shopkart.authorization.oauth2.security.repositories.JpaRegisteredClientRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClientRegistrationService implements IClientRegistrationService{

    private PasswordEncoder passwordEncoder;
    private JpaRegisteredClientRepository jpaRegisteredClientRepository;
    public ClientRegistrationService(PasswordEncoder passwordEncoder,
                                     JpaRegisteredClientRepository jpaRegisteredClientRepository){
        this.passwordEncoder=passwordEncoder;
        this.jpaRegisteredClientRepository=jpaRegisteredClientRepository;
    }

    @Override
    public void createClient(ClientRegistrationRequestRecord requestRecord) {
        boolean isClientExist=jpaRegisteredClientRepository.existsByClientId(requestRecord.clientId());
        if(isClientExist)
            throw new ClientAlreadyExistException("Client already exists with client id: "+requestRecord.clientId());

        jpaRegisteredClientRepository.save(from(requestRecord));
    }

    @Override
    public RegisteredClient findById(String id) {
        return jpaRegisteredClientRepository.findById(id);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        return jpaRegisteredClientRepository.findByClientId(clientId);
    }

    private RegisteredClient from(ClientRegistrationRequestRecord record){
        return RegisteredClient.withId(UUID.randomUUID().toString())
            .clientId(record.clientId())
            .clientSecret(passwordEncoder.encode(record.clientSecret()))
            .clientName(record.clientName())
            .redirectUris(uris->uris.addAll(record.redirectUris()))
            .postLogoutRedirectUris(uris->uris.addAll(record.postLogoutRedirectUris()))
            .scopes(scops->scops.addAll(record.scopes()))
            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
            .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
            .build();
    }
}
