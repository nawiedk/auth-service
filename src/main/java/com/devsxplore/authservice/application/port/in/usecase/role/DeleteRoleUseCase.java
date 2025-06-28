package com.devsxplore.authservice.application.port.in.usecase.role;

import com.devsxplore.authservice.application.port.in.command.role.DeleteRoleCommand;

public interface DeleteRoleUseCase {
    void delete(DeleteRoleCommand command);
}
