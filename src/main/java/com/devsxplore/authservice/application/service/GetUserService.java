package com.devsxplore.authservice.application.service;

import com.devsxplore.authservice.application.port.in.command.GetUserCommand;
import com.devsxplore.authservice.application.port.in.usecase.GetUserUseCase;
import com.devsxplore.authservice.application.port.out.UserRepositoryPort;
import com.devsxplore.authservice.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class GetUserService implements GetUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public User getUserByUserId(GetUserCommand command) {
        return userRepositoryPort.findUserByUserId(command.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepositoryPort.findAllUsers();
    }
}
