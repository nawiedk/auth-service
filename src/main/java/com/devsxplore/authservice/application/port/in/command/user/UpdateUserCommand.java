package com.devsxplore.authservice.application.port.in.command.user;

import com.devsxplore.authservice.domain.util.UserStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import static com.devsxplore.authservice.domain.validation.Validation.validate;

public record UpdateUserCommand(
        @NotNull @Min(0) Long userId,
        @NotBlank String username,
        @NotBlank String email,
        @NotBlank String password,
        @NotBlank UserStatus status
) {

    public UpdateUserCommand(Long userId, String username, String email, String password, UserStatus status) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.status = status;

        validate(this);
    }
}
