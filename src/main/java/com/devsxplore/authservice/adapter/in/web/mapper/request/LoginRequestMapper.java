package com.devsxplore.authservice.adapter.in.web.mapper.request;

import com.devsxplore.authservice.adapter.in.web.dto.LoginUserDto;
import com.devsxplore.authservice.application.port.in.command.LoginUserCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginRequestMapper {

    public LoginUserCommand generateLoginUserCommand(LoginUserDto dto) {
        return new LoginUserCommand(
                dto.getEmail(),
                dto.getPassword()
        );
    }
}
