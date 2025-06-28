package com.devsxplore.authservice.application.port.in.usecase.role;

import com.devsxplore.authservice.application.port.in.command.role.CreateRoleCommand;
import com.devsxplore.authservice.domain.model.Role;

public interface CreateRoleUseCase {
    Role create(CreateRoleCommand command);
}
