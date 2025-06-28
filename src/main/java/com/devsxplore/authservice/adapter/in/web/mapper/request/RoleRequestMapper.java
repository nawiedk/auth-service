package com.devsxplore.authservice.adapter.in.web.mapper.request;

import com.devsxplore.authservice.adapter.in.web.dto.RoleCreateDto;
import com.devsxplore.authservice.application.port.in.command.role.CreateRoleCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleRequestMapper {

    public CreateRoleCommand generateCreateRoleCommand(RoleCreateDto dto) {
        return new CreateRoleCommand(dto.getName());
    }
}
