package com.shopkart.authorization.oauth2.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity(name = "roles")
@NoArgsConstructor
public class Role extends BaseModel {
    private String roleName;

    public Role(String name) {
        this.roleName=name;
    }
    public static Set<String> toStrings(Set<Role> roles){
        if(roles==null || roles.isEmpty()) return Set.of();
        return roles.stream().map(Role::getRoleName).collect(Collectors.toSet());
    }

    public static Set<Role> toRoles(Set<String> roleNames){
        if(roleNames==null || roleNames.isEmpty()) return Set.of();
        return roleNames.stream().map(Role::new).collect(Collectors.toSet());
    }
}
