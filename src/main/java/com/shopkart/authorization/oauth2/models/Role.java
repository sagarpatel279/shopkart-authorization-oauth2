package com.shopkart.authorization.oauth2.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "roles")
@NoArgsConstructor
public class Role extends BaseModel {
    private String roleName;
}
