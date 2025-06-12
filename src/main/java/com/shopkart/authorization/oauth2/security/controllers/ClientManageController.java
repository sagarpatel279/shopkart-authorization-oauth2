package com.shopkart.authorization.oauth2.security.controllers;

import com.shopkart.authorization.oauth2.security.records.ClientRegistrationRequestRecord;
import com.shopkart.authorization.oauth2.security.services.CustomRegisteredClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientManageController {

    private CustomRegisteredClientService customRegisteredClientService;

    public ClientManageController(CustomRegisteredClientService customRegisteredClientService){
        this.customRegisteredClientService=customRegisteredClientService;
    }

    public ResponseEntity<?> createClient(ClientRegistrationRequestRecord clientRegistrationRequestRecord){
        customRegisteredClientService.registerClientByClientRequestRecord(clientRegistrationRequestRecord);
        return ResponseEntity.ok("Client registered successfully");
    }

}
