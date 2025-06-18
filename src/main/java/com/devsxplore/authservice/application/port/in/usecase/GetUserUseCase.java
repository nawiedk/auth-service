package com.devsxplore.authservice.application.port.in.usecase;

import com.devsxplore.authservice.application.port.in.command.GetUserCommand;
import com.devsxplore.authservice.domain.model.User;

import java.util.List;

public interface GetUserUseCase {
    User getUserByUserId(GetUserCommand command);
    List<User> getAllUsers();
}
