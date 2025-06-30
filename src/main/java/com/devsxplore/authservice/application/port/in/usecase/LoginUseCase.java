package com.devsxplore.authservice.application.port.in.usecase;

import com.devsxplore.authservice.application.port.in.command.LoginUserCommand;

public interface LoginUseCase {
    String login(LoginUserCommand command);

    String loginAdmin(LoginUserCommand command);
}
