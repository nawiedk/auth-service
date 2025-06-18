package com.devsxplore.authservice.application.port.in.usecase;

import com.devsxplore.authservice.application.port.in.command.DeleteUserCommand;

public interface DeleteUserUseCase {
    void deleteUserByUserId(DeleteUserCommand command);
}
