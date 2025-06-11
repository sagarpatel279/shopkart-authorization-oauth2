package com.shopkart.authorization.oauth2.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    public AuthenticationController() {

    }

    @PostMapping("/signup")
    public ResponseEntity<?> doSignUp() {
        return null;
    }
    @PostMapping("/login")
    public ResponseEntity<?> doLogin() {
        return null;
    }

    @PostMapping("/logout")
    public ResponseEntity<?> doLogout() {
        return null;
    }

}
