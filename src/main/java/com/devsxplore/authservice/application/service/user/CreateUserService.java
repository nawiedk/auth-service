package com.devsxplore.authservice.application.service.user;

import com.devsxplore.authservice.application.port.in.command.user.CreateUserCommand;
import com.devsxplore.authservice.application.port.in.usecase.user.CreateUserUseCase;
import com.devsxplore.authservice.application.port.out.RoleRepositoryPort;
import com.devsxplore.authservice.application.port.out.UserRepositoryPort;
import com.devsxplore.authservice.domain.model.User;
import com.devsxplore.authservice.domain.util.UserStatus;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateUserService implements CreateUserUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final RoleRepositoryPort roleRepositoryPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User create(CreateUserCommand command) {
        var defaultRole = roleRepositoryPort.findRoleByName("USER")
                .orElseThrow(() -> new EntityNotFoundException("Default role not found"));

        var userByUsername = userRepositoryPort.findUserByUsername(command.username());
        var userByEmail = userRepositoryPort.findUserByEmail(command.email());

        if (userByUsername.isPresent()) {
            throw new IllegalArgumentException("Username already exists!");
        } else if (userByEmail.isPresent()) {
            throw new IllegalArgumentException("Email already exists!");
        }

        User user = User.userWithoutId(
                command.username(),
                command.email(),
                passwordEncoder.encode(command.password()),
                UserStatus.ACTIVE,
                Set.of(defaultRole)
        );

        return userRepositoryPort.save(user);
    }

    public User initAdmin(CreateUserCommand command) {
        var defaultRole = roleRepositoryPort.findRoleByName("USER")
                .orElseThrow(() -> new EntityNotFoundException("Default role not found"));
        var adminRole = roleRepositoryPort.findRoleByName("ADMIN")
                .orElseThrow(() -> new EntityNotFoundException("Admin role not found"));

        var userByUsername = userRepositoryPort.findUserByUsername(command.username());
        var userByEmail = userRepositoryPort.findUserByEmail(command.email());

        if (userByUsername.isPresent()) {
            throw new IllegalArgumentException("Username already exists!");
        } else if (userByEmail.isPresent()) {
            throw new IllegalArgumentException("Email already exists!");
        }

        User user = User.userWithoutId(
                command.username(),
                command.email(),
                passwordEncoder.encode(command.password()),
                UserStatus.ACTIVE,
                Set.of(defaultRole, adminRole)
        );
        return userRepositoryPort.save(user);
    }
}
