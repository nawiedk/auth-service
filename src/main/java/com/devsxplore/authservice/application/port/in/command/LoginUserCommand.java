package com.devsxplore.authservice.application.port.in.command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import static com.devsxplore.authservice.domain.validation.Validation.validate;

public record LoginUserCommand(
        @NotBlank @Email String email,
        @NotBlank String password
) {
    public LoginUserCommand(String email, String password) {
        this.email = email;
        this.password = password;
        validate(this);
    }
}
