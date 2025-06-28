package com.devsxplore.authservice.application.port.in.command.role;

import jakarta.validation.constraints.NotBlank;

import static com.devsxplore.authservice.domain.validation.Validation.validate;

public record CreateRoleCommand(@NotBlank String name) {
    public CreateRoleCommand(String name) {
        this.name = name;
        validate(this);
    }
}
