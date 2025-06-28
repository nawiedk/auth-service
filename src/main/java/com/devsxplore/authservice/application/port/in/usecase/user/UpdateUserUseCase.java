package com.devsxplore.authservice.application.port.in.usecase.user;

import com.devsxplore.authservice.application.port.in.command.user.UpdateUserCommand;
import com.devsxplore.authservice.domain.model.User;

public interface UpdateUserUseCase {
    User update(UpdateUserCommand command);
}
