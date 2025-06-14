package com.shopkart.authorization.oauth2.models;

import com.shopkart.authorization.oauth2.dtos.UserRecord;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public static UserRecord toUserRecord(User user){
        return new UserRecord(user.getId(),user.getEmail(),Role.toStrings(user.getRoles()));
    }
}
