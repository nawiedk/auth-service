package com.devsxplore.authservice.adapter.in.web.mapper.response;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginResponseMapper {

    public LoginResponse mapToResponse(String token) {
        return new LoginResponse(token);
    }
}
