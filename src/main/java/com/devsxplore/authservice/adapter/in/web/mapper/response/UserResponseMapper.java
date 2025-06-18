package com.devsxplore.authservice.adapter.in.web.mapper.response;

import com.devsxplore.authservice.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserResponseMapper {

    public UserResponse mapToResponse(User entity) {
        return new UserResponse(
                entity.getUserId().map(User.UserId::userId).orElse(null),
                entity.getUsername(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getStatus()
        );
    }
}
