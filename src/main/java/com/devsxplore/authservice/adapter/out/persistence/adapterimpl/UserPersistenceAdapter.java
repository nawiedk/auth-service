package com.devsxplore.authservice.adapter.out.persistence.adapterimpl;

import com.devsxplore.authservice.adapter.out.persistence.mapper.UserMapper;
import com.devsxplore.authservice.adapter.out.persistence.repository.UserRepository;
import com.devsxplore.authservice.application.port.out.UserRepositoryPort;
import com.devsxplore.authservice.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserRepositoryPort {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User save(User user) {
        return userMapper.mapToDomainEntity(
                userRepository.save(userMapper.mapToJpaEntity(user))
        );
    }

    @Override
    public Optional<User> findUserByUserId(Long userId) {
        return userRepository.findById(userId)
                .map(userMapper::mapToDomainEntity);
    }

    @Override
    public void deleteByUserId(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public boolean existsByUserId(Long userId) {
        return userRepository.existsById(userId);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::mapToDomainEntity)
                .toList();
    }
}
