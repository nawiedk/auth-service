package com.devsxplore.authservice.adapter.out.persistence.mapper;

import com.devsxplore.authservice.adapter.out.persistence.jpaentity.RoleJpaEntity;
import com.devsxplore.authservice.domain.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleMapper {

    public Role mapToDomainEntity(RoleJpaEntity entity) {
        return Role.roleWithId(
                new Role.RoleId(entity.getRoleId()),
                entity.getName()
        );
    }

    public RoleJpaEntity mapToJpaEntity(Role role) {
        RoleJpaEntity entity = new RoleJpaEntity();
        entity.setRoleId(role.getRoleId().map(Role.RoleId::roleId).orElse(null));
        entity.setName(role.getName());
        return entity;
    }
}
