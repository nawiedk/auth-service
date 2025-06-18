package com.devsxplore.authservice.adapter.out.persistence.mapper;

import com.devsxplore.authservice.adapter.out.persistence.jpaentity.UserJpaEntity;
import com.devsxplore.authservice.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public User mapToDomainEntity(UserJpaEntity entity) {
        return User.userWithId(
                new User.UserId(entity.getUserId()),
                entity.getUsername(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getStatus()
        );
    }

    public UserJpaEntity mapToJpaEntity(User user) {
        UserJpaEntity entity = new UserJpaEntity();
        entity.setUserId(user.getUserId().map(User.UserId::userId).orElse(null));
        entity.setUsername(user.getUsername());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        entity.setStatus(user.getStatus());
        return entity;
    }
}
