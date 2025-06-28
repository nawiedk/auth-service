package com.devsxplore.authservice.application.port.in.usecase;

import com.devsxplore.authservice.application.port.in.command.user.CreateUserCommand;

public interface RegisterUseCase {
    String register(CreateUserCommand command);

    String registerAdmin(CreateUserCommand command);
}
