package com.devsxplore.authservice.application.port.in.command;

import jakarta.validation.constraints.NotBlank;

import static com.devsxplore.authservice.domain.validation.Validation.validate;

public record CreateUserCommand(
        @NotBlank String username,
        @NotBlank String email,
        @NotBlank String password
) {

    public CreateUserCommand(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        validate(this);
    }
}
