package com.shopkart.authorization.oauth2.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity(name = "users")
public class User extends BaseModel{
    @Column(unique = true)
    private String email;
    private String passwordSalt;
    @ManyToMany
    private Set<Role> roles;

}
