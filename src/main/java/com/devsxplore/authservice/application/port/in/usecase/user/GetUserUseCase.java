package com.devsxplore.authservice.application.port.in.usecase.user;

import com.devsxplore.authservice.application.port.in.command.user.GetUserByUsernameCommand;
import com.devsxplore.authservice.application.port.in.command.user.GetUserCommand;
import com.devsxplore.authservice.domain.model.User;

import java.util.List;

public interface GetUserUseCase {
    User getUserByUserId(GetUserCommand command);

    boolean userExistsByUsername(GetUserByUsernameCommand command);

    List<User> getAllUsers();
}
