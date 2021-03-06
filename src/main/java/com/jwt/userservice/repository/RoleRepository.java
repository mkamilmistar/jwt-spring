package com.jwt.userservice.repository;

import com.jwt.userservice.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
  Role findByName(String name);
}
