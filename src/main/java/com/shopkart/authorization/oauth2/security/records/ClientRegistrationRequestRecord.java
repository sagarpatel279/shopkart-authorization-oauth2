package com.shopkart.authorization.oauth2.security.records;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

import java.util.Set;
import java.util.UUID;

public record ClientRegistrationRequestRecord(
        String clientId,
        String clientSecret,
        String clientName,
        Set<String> redirectUris,
        Set<String> postLogoutRedirectUris,
        Set<String> scopes){

}

//{
//        "clientId":"",
//        "clientSecret":"",
//        "clientName":"",
//        "redirectUris":{"":""},
//        "postLogoutRedirectUris":{"":""},
//        "scopes":{"":""}
//}