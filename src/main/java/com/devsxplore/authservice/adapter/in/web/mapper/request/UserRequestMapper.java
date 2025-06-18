package com.devsxplore.authservice.adapter.in.web.mapper.request;

import com.devsxplore.authservice.adapter.in.web.dto.UserCreateDto;
import com.devsxplore.authservice.adapter.in.web.dto.UserUpdateDto;
import com.devsxplore.authservice.application.port.in.command.CreateUserCommand;
import com.devsxplore.authservice.application.port.in.command.UpdateUserCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRequestMapper {

    public CreateUserCommand generateCreateUserCommand(UserCreateDto dto){
        return new CreateUserCommand(
                dto.getUsername(),
                dto.getEmail(),
                dto.getPassword()
        );
    }

    public UpdateUserCommand generateUpdateUserCommand(UserUpdateDto dto){
        return new UpdateUserCommand(
                dto.getUserId(),
                dto.getUsername(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getStatus()
        );
    }

}
