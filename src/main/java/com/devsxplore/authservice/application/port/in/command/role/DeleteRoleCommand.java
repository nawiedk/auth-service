package com.devsxplore.authservice.application.port.in.command.role;

import jakarta.validation.constraints.NotNull;

import static com.devsxplore.authservice.domain.validation.Validation.validate;

public record DeleteRoleCommand(@NotNull String name) {

    public DeleteRoleCommand(String name) {
        this.name = name;
        validate(this);
    }
}
