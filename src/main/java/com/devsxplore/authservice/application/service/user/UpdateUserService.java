package com.devsxplore.authservice.application.service.user;

import com.devsxplore.authservice.application.port.in.command.user.UpdateUserCommand;
import com.devsxplore.authservice.application.port.in.usecase.user.UpdateUserUseCase;
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
        var user = userRepositoryPort.findUserByUserId(command.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        var userByUsername = userRepositoryPort.findUserByUsername(command.username());
        var userByEmail = userRepositoryPort.findUserByEmail(command.email());

        if (userByUsername.isPresent() && !userByUsername.get().getUserId().equals(command.userId())) {
            throw new IllegalArgumentException("Username already exists!");
        } else if (userByEmail.isPresent() && !userByEmail.get().getUserId().equals(command.userId())) {
            throw new IllegalArgumentException("Email already exists!");
        }

        User updatedUser = User.userWithId(
                new User.UserId(command.userId()),
                command.username(),
                command.email(),
                passwordEncoder.encode(command.password()),
                command.status(),
                user.getRoles()
        );
        return userRepositoryPort.save(updatedUser);
    }
}
