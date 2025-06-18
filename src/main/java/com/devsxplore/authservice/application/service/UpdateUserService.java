package com.devsxplore.authservice.application.service;

import com.devsxplore.authservice.application.port.in.command.UpdateUserCommand;
import com.devsxplore.authservice.application.port.in.usecase.UpdateUserUseCase;
import com.devsxplore.authservice.application.port.out.UserRepositoryPort;
import com.devsxplore.authservice.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdateUserService implements UpdateUserUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User update(UpdateUserCommand command) {
        userRepositoryPort.findUserByUserId(command.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        User updatedUser = User.userWithId(
                new User.UserId(command.userId()),
                command.username(),
                command.email(),
                passwordEncoder.encode(command.password()),
                command.status()
        );
        return userRepositoryPort.save(updatedUser);
    }
}
