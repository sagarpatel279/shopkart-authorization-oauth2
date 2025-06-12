package com.shopkart.authorization.oauth2.security.records;
import com.shopkart.authorization.oauth2.security.models.Client;

import java.time.Instant;

public record ClientRegistrationRequestRecord(
        String clientId,
        Instant clientIdIssuedAt,
        String clientSecret,
        Instant clientSecretExpiresAt,
        String clientName,
        String clientAuthenticationMethods,
        String authorizationGrantTypes,
        String redirectUris,
        String postLogoutRedirectUris,
        String scopes,
        String clientSettings,
        String tokenSettings){
    public Client from(ClientRegistrationRequestRecord record){
        Client client=new Client();
        client.setClientId(record.clientId);
        client.setClientIdIssuedAt(record.clientIdIssuedAt);
        client.setClientName(record.clientName);
        client.setClientSecret(record.clientSecret);
        client.setClientSecretExpiresAt(record.clientSecretExpiresAt);
        client.setClientAuthenticationMethods();
        client.setAuthorizationGrantTypes();
        client.setRedirectUris();
        client.setPostLogoutRedirectUris();
        client.setScopes();
        client.setClientSettings();
        client.setTokenSettings();
    }
}
