package com.devsxplore.authservice.application.port.out;

import com.devsxplore.authservice.domain.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepositoryPort {
    Role save(Role role);

    void deleteByName(String name);

    List<Role> findAllRoles();

    Optional<Role> findRoleByName(String name);
}
