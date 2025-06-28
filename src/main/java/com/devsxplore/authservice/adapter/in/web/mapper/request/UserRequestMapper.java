package com.devsxplore.authservice.adapter.in.web.mapper.request;

import com.devsxplore.authservice.adapter.in.web.dto.RegisterUserDto;
import com.devsxplore.authservice.adapter.in.web.dto.UserUpdateDto;
import com.devsxplore.authservice.application.port.in.command.user.CreateUserCommand;
import com.devsxplore.authservice.application.port.in.command.user.UpdateUserCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRequestMapper {

    public CreateUserCommand generateCreateUserCommand(RegisterUserDto dto) {
        return new CreateUserCommand(
                dto.getUsername(),
                dto.getEmail(),
                dto.getPassword()
        );
    }

    public UpdateUserCommand generateUpdateUserCommand(UserUpdateDto dto) {
        return new UpdateUserCommand(
                dto.getUserId(),
                dto.getUsername(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getStatus()
        );
    }

}
