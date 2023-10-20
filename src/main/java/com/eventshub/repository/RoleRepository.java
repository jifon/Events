package com.eventshub.repository;

import java.util.Optional;

import com.eventshub.model.enums.ERole;
import com.eventshub.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}