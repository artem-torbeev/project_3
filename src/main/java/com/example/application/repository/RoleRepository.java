package com.example.application.repository;

import com.example.application.model.Role;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;


public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findRoleById (Long id);
}
