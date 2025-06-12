package com.shopkart.authorization.oauth2.security.controllers;

import com.shopkart.authorization.oauth2.security.records.ClientRegistrationRequestRecord;
import com.shopkart.authorization.oauth2.security.services.IClientRegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientManageController {
    private IClientRegistrationService clientRegistrationService;

    public ClientManageController(IClientRegistrationService clientRegistrationService){
        this.clientRegistrationService=clientRegistrationService;
    }
    @PostMapping()
    public ResponseEntity<?> createClient(@RequestBody ClientRegistrationRequestRecord clientRegistrationRequestRecord){
        clientRegistrationService.createClient(ClientRegistrationRequestRecord.from(clientRegistrationRequestRecord));
        return ResponseEntity.ok("Client registered successfully");
    }

}
