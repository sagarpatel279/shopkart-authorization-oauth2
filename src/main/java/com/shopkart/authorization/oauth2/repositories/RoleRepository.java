package com.shopkart.authorization.oauth2.repositories;

import com.shopkart.authorization.oauth2.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
