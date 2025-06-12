package com.shopkart.authorization.oauth2.services;


import com.shopkart.authorization.oauth2.dtos.SignUpResponseRecord;

public interface IAuthService {
    SignUpResponseRecord signup(String email, String password);
}
