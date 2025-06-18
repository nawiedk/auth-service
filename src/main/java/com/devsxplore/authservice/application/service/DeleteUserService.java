package com.devsxplore.authservice.application.service;

import com.devsxplore.authservice.application.port.in.command.DeleteUserCommand;
import com.devsxplore.authservice.application.port.in.usecase.DeleteUserUseCase;
import com.devsxplore.authservice.application.port.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DeleteUserService implements DeleteUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public void deleteUserByUserId(DeleteUserCommand command) {
        if(!userRepositoryPort.existsByUserId(command.userId())) {
            throw new RuntimeException("User not found");
        }
        userRepositoryPort.deleteByUserId(command.userId());
    }
}
