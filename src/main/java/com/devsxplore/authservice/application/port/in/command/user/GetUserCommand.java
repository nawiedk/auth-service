package com.devsxplore.authservice.application.port.in.command.user;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import static com.devsxplore.authservice.domain.validation.Validation.validate;

public record GetUserCommand(
        @NotNull @Min(0) Long userId
) {

    public GetUserCommand(Long userId) {
        this.userId = userId;
        validate(this);
    }
}
