package com.devsxplore.authservice.application.port.in.command.user;

import jakarta.validation.constraints.NotBlank;

import static com.devsxplore.authservice.domain.validation.Validation.validate;

public record GetUserByUsernameCommand(@NotBlank String Username) {
    public GetUserByUsernameCommand(String Username) {
        this.Username = Username;
        validate(this);
    }
}
