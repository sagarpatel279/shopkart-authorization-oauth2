package com.shopkart.authorization.oauth2.dtos;

import java.util.Set;

public record UserRecord(Long userId, String email, Set<String> roles) {
}
