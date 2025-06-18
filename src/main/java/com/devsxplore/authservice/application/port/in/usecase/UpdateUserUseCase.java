package com.devsxplore.authservice.application.port.in.usecase;

import com.devsxplore.authservice.application.port.in.command.UpdateUserCommand;
import com.devsxplore.authservice.domain.model.User;

public interface UpdateUserUseCase {
    User update(UpdateUserCommand command);
}
