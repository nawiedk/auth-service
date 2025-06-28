package com.devsxplore.authservice.application.service;

import com.devsxplore.authservice.application.port.in.command.user.CreateUserCommand;
import com.devsxplore.authservice.application.port.in.usecase.RegisterUseCase;
import com.devsxplore.authservice.application.port.in.usecase.user.CreateUserUseCase;
import com.devsxplore.authservice.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class RegisterUserService implements RegisterUseCase {

    private final CreateUserUseCase createUserUseCase;
    private final JwtService jwtService;

    @Override
    public String register(CreateUserCommand command) {
        var user = createUserUseCase.create(command);

        return getUserToken(user);
    }

    @Override
    public String registerAdmin(CreateUserCommand command) {
        var user = createUserUseCase.initAdmin(command);

        return getUserToken(user);
    }

    private String getUserToken(User user) {
        var userDetails = new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(role -> new org.springframework.security.core.authority.SimpleGrantedAuthority("ROLE_" + role.getName()))
                        .toList()
        );

        return jwtService.generateToken(
                userDetails,
                user.getUserId().map(User.UserId::userId).orElseThrow()
        );
    }
}
