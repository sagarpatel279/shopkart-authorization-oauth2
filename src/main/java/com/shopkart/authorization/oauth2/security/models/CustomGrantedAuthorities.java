package com.shopkart.authorization.oauth2.security.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.shopkart.authorization.oauth2.models.Role;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@JsonDeserialize
@NoArgsConstructor
public class CustomGrantedAuthorities implements GrantedAuthority {
    private String authority;

    public CustomGrantedAuthorities(Role role){
        this.authority=role.getRoleName();
    }
    @Override
    public String getAuthority() {
        return this.authority;
    }
}
