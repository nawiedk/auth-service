package com.devsxplore.authservice.application.service;

import com.devsxplore.authservice.application.port.in.command.CreateUserCommand;
import com.devsxplore.authservice.application.port.in.usecase.CreateUserUseCase;
import com.devsxplore.authservice.application.port.out.UserRepositoryPort;
import com.devsxplore.authservice.domain.model.User;
import com.devsxplore.authservice.domain.util.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateUserService implements CreateUserUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User create(CreateUserCommand command) {
        User user = User.userWithoutId(
                command.username(),
                command.email(),
                passwordEncoder.encode(command.password()),
                UserStatus.ACTIVE
        );
        return userRepositoryPort.save(user);
    }
}
