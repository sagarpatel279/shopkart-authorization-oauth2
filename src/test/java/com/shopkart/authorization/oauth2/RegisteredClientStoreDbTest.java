package com.shopkart.authorization.oauth2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

import java.util.UUID;

@SpringBootTest
public class RegisteredClientStoreDbTest {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RegisteredClientRepository registeredClientRepository;

//    @Test
    void saveClientInDb(){
        RegisteredClient oidcClient = RegisteredClient.withId(UUID.randomUUID().toString())
        .clientId("productService")
        .clientSecret(passwordEncoder.encode("password"))
        .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
        .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
        .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
        .redirectUri("http://localhost:9090/login/oauth2/code/productService")
        .postLogoutRedirectUri("http://localhost:9090/")
        .scope(OidcScopes.OPENID)
        .scope(OidcScopes.PROFILE)
        .scope("ADMIN")
        .scope("STUDENT")
        .scope("MENTOR")
        .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
        .build();

        registeredClientRepository.save(oidcClient);
    }
}
