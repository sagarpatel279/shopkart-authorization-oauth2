package com.shopkart.authorization.oauth2.repositories;

import com.shopkart.authorization.oauth2.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmailAndIsDeletedIsFalse(String email);
    Optional<User> findByEmailAndIsDeletedIsFalse(String username);
}
