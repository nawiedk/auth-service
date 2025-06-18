package com.devsxplore.authservice.adapter.in.web.mapper.response;

import com.devsxplore.authservice.domain.util.UserStatus;

public record UserResponse (
        Long userId,
        String username,
        String email,
        String password,
        UserStatus status
){
}
