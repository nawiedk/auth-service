package com.devsxplore.authservice.application.port.in.usecase;

import com.devsxplore.authservice.application.port.in.command.CreateUserCommand;
import com.devsxplore.authservice.domain.model.User;

public interface CreateUserUseCase {
    User create(CreateUserCommand command);
}
