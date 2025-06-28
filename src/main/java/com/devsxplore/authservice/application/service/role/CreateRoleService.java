package com.devsxplore.authservice.application.service.role;

import com.devsxplore.authservice.application.port.in.command.role.CreateRoleCommand;
import com.devsxplore.authservice.application.port.in.usecase.role.CreateRoleUseCase;
import com.devsxplore.authservice.application.port.out.RoleRepositoryPort;
import com.devsxplore.authservice.domain.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateRoleService implements CreateRoleUseCase {

    private final RoleRepositoryPort roleRepositoryPort;

    @Override
    public Role create(CreateRoleCommand command) {
        Role role = Role.roleWithoutId(command.name());

        return roleRepositoryPort.save(role);
    }
}
