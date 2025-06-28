package com.devsxplore.authservice.application.port.in.usecase.user;

import com.devsxplore.authservice.application.port.in.command.user.CreateUserCommand;
import com.devsxplore.authservice.domain.model.User;

public interface CreateUserUseCase {
    User create(CreateUserCommand command);

    User initAdmin(CreateUserCommand command);
}
