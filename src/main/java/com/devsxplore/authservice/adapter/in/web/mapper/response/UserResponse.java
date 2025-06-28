package com.devsxplore.authservice.adapter.in.web.mapper.response;

import com.devsxplore.authservice.domain.util.UserStatus;

import java.util.Set;

public record UserResponse(
        Long userId,
        String username,
        String email,
        UserStatus status,
        Set<RoleResponse> roles
) {
}
