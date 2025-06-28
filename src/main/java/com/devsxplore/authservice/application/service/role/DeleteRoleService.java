package com.devsxplore.authservice.application.service.role;

import com.devsxplore.authservice.application.port.in.command.role.DeleteRoleCommand;
import com.devsxplore.authservice.application.port.in.usecase.role.DeleteRoleUseCase;
import com.devsxplore.authservice.application.port.out.RoleRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DeleteRoleService implements DeleteRoleUseCase {

    private final RoleRepositoryPort roleRepositoryPort;

    @Override
    public void delete(DeleteRoleCommand command) {
        roleRepositoryPort.deleteByName(command.name());
    }
}
