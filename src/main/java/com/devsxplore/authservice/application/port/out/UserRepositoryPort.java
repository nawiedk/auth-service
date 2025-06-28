package com.devsxplore.authservice.application.port.out;

import com.devsxplore.authservice.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {
    User save(User user);

    Optional<User> findUserByUserId(Long userId);

    void deleteByUserId(Long userId);

    boolean existsByUserId(Long userId);

    List<User> findAllUsers();

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByUsername(String username);
}
