package com.shopkart.authorization.oauth2.security.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.shopkart.authorization.oauth2.models.User;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@JsonDeserialize
@NoArgsConstructor
public class CustomUserDetails implements UserDetails {
    private Long userId;
    private String username;
    private String password;
    private Set<? extends GrantedAuthority> authorities;
    private boolean accountNonExpired=true;
    private boolean accountNonLocked=true;
    private boolean credentialsNonExpired=true;
    private boolean enabled=true;


    public Long getUserId() {
        return userId;
    }

    public CustomUserDetails(User user){
        this.username=user.getEmail();
        this.password=user.getPasswordSalt();
        this.authorities=user.getRoles()
                .stream().map(CustomGrantedAuthorities::new)
                .collect(Collectors.toCollection(HashSet::new));
        this.userId=user.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

}
