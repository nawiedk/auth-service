package com.devsxplore.authservice.adapter.in.web.mapper.response;

import org.springframework.stereotype.Component;

@Component
public class AuthResponseMapper {
    public AuthResponse toAuthResponse(String jwtToken) {
        return new AuthResponse(jwtToken);
    }
}
