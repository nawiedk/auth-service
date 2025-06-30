package com.devsxplore.authservice.application.service;

import com.devsxplore.authservice.application.port.in.command.LoginUserCommand;
import com.devsxplore.authservice.application.port.in.usecase.LoginUseCase;
import com.devsxplore.authservice.application.port.out.UserRepositoryPort;
import com.devsxplore.authservice.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginUserService implements LoginUseCase {
    private final UserRepositoryPort userRepositoryPort;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public String login(LoginUserCommand command) {
        var user = userRepositoryPort.findUserByEmail(command.email())
                .orElseThrow(() -> new IllegalArgumentException("This user does not exist"));

        if (!passwordEncoder.matches(command.password(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        var userDetails = new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                        .toList()
        );

        return jwtService.generateToken(
                userDetails,
                user.getUserId().map(User.UserId::userId).orElseThrow()
        );
    }

    @Override
    public String loginAdmin(LoginUserCommand command) {
        var user = userRepositoryPort.findUserByEmail(command.email())
                .orElseThrow(() -> new IllegalArgumentException("This user does not exist"));

        if (!passwordEncoder.matches(command.password(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        if (user.getRoles().stream().noneMatch(role -> role.getName().equalsIgnoreCase("ADMIN"))) {
            throw new IllegalArgumentException("Access denied. User is not an admin");
        }

        var userDetails = new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                        .toList()
        );

        return jwtService.generateToken(
                userDetails,
                user.getUserId().map(User.UserId::userId).orElseThrow()
        );
    }
}