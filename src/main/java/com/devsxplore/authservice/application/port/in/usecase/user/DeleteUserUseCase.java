package com.devsxplore.authservice.application.port.in.usecase.user;

import com.devsxplore.authservice.application.port.in.command.user.DeleteUserCommand;

public interface DeleteUserUseCase {
    void deleteUserByUserId(DeleteUserCommand command);
}
