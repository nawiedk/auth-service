package com.devsxplore.authservice.adapter.in.web.mapper.response;

import com.devsxplore.authservice.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserResponseMapper {

    public UserResponse mapToResponse(User user) {
        return new UserResponse(
                user.getUserId().map(User.UserId::userId).orElse(null),
                user.getUsername(),
                user.getEmail(),
                user.getStatus(),
                user.getRoles().stream()
                        .map(role -> new RoleResponse(role.getName()))
                        .collect(Collectors.toSet())
        );
    }
}
