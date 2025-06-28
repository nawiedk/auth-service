package com.devsxplore.authservice.domain.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.Optional;

import static com.devsxplore.authservice.domain.validation.Validation.validate;

public class Role {

    @Valid
    private final RoleId roleId;

    @Getter
    @NotBlank
    private final String name;

    public Role(RoleId roleId, String name) {
        this.roleId = roleId;
        this.name = name;
    }

    public record RoleId(Long roleId) {
        public RoleId(@NotNull Long roleId) {
            if (roleId == null || roleId <= 0)
                throw new IllegalArgumentException("Role ID must be a positive number.");
            this.roleId = roleId;
            validate(this);
        }
    }

    public static Role roleWithId(RoleId roleId, String name) {
        return new Role(roleId, name);
    }

    public static Role roleWithoutId(String name) {
        return new Role(null, name);
    }

    public Optional<RoleId> getRoleId() {
        return Optional.ofNullable(this.roleId);
    }
}
