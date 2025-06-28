package com.devsxplore.authservice.adapter.out.persistence.adapterimpl;

import com.devsxplore.authservice.adapter.out.persistence.mapper.RoleMapper;
import com.devsxplore.authservice.adapter.out.persistence.repository.RoleRepository;
import com.devsxplore.authservice.application.port.out.RoleRepositoryPort;
import com.devsxplore.authservice.domain.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RolePersistenceAdapter implements RoleRepositoryPort {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public Role save(Role role) {
        return roleMapper.mapToDomainEntity(
                roleRepository.save(roleMapper.mapToJpaEntity(role))
        );
    }

    @Override
    public void deleteByName(String name) {
        roleRepository.deleteByName(name);
    }

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::mapToDomainEntity)
                .toList();
    }

    @Override
    public Optional<Role> findRoleByName(String name) {
        return roleRepository.findByName(name)
                .map(roleMapper::mapToDomainEntity);

    }
}
