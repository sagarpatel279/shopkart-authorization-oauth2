package com.shopkart.authorization.oauth2.controllers;

import com.shopkart.authorization.oauth2.dtos.ApiResponseRecord;
import com.shopkart.authorization.oauth2.dtos.SignUpRequestRecord;
import com.shopkart.authorization.oauth2.dtos.SignUpResponseRecord;
import com.shopkart.authorization.oauth2.services.IAuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private IAuthService authService;

    public AuthenticationController(IAuthService authService) {
        this.authService=authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> doSignUp(@RequestBody SignUpRequestRecord signUpRequestRecord) {
        SignUpResponseRecord responseRecord=authService.signup(signUpRequestRecord.email(),
                signUpRequestRecord.password());
        ApiResponseRecord<SignUpResponseRecord> apiResponse=new ApiResponseRecord<>(responseRecord,
                "User has registered successfully", HttpStatus.CREATED.value());
        return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
    }


    @PostMapping("/xyz")
    public ResponseEntity<?> doXyz() {
        return ResponseEntity.ok("Welcome to Shop Kart...!");
    }

}
