package com.shopkart.authorization.oauth2.security.records;
import com.shopkart.authorization.oauth2.security.models.Client;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

public record ClientRegistrationRequestRecord(
        String clientId,
        String clientSecret,
        String clientName,
        Set<String> redirectUris,
        Set<String> postLogoutRedirectUris,
        Set<String> scopes){
    public static RegisteredClient from(ClientRegistrationRequestRecord record){
        return RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId(record.clientId)
        .clientName(record.clientName)
        .clientSecret(record.clientSecret)
        .redirectUris(uris->uris.addAll(record.redirectUris))
        .postLogoutRedirectUris(uris->uris.addAll(record.postLogoutRedirectUris))
        .scopes(scops->scops.addAll(record.scopes)).build();
    }
}

//{
//        "clientId":"",
//        "clientSecret":"",
//        "clientName":"",
//        "redirectUris":{"":""},
//        "postLogoutRedirectUris":{"":""},
//        "scopes":{"":""}
//}