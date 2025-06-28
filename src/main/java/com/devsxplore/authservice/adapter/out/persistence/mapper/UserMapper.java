package com.devsxplore.authservice.adapter.out.persistence.mapper;

import com.devsxplore.authservice.adapter.out.persistence.jpaentity.UserJpaEntity;
import com.devsxplore.authservice.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final RoleMapper roleMapper;

    public User mapToDomainEntity(UserJpaEntity entity) {
        return User.userWithId(
                new User.UserId(entity.getUserId()),
                entity.getUsername(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getStatus(),
                entity.getRoles().stream().map(roleMapper::mapToDomainEntity).collect(Collectors.toSet())
        );
    }

    public UserJpaEntity mapToJpaEntity(User user) {
        UserJpaEntity entity = new UserJpaEntity();
        entity.setUserId(user.getUserId().map(User.UserId::userId).orElse(null));
        entity.setUsername(user.getUsername());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        entity.setStatus(user.getStatus());
        entity.setRoles(user.getRoles().stream().map(roleMapper::mapToJpaEntity).collect(Collectors.toSet()));
        return entity;
    }
}
