package com.devsxplore.authservice.domain.model;


import com.devsxplore.authservice.domain.util.UserStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.Optional;
import java.util.Set;

import static com.devsxplore.authservice.domain.validation.Validation.validate;

public class User {

    @Valid
    private final UserId userId;

    @Getter
    @NotBlank
    @Size(min = 3, max = 25)
    private final String username;

    @Getter
    @NotBlank
    @Email
    private final String email;

    @Getter
    @NotBlank
    @Size(min = 7, max = 50)
    private final String password;

    @Getter
    @NotNull
    private final UserStatus status;

    @Getter
    @NotNull
    private final Set<@Valid Role> roles;


    public User(
            UserId userId,
            String username,
            String email,
            String password,
            UserStatus status,
            Set<Role> roles
    ) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.status = status;
        this.roles = roles;
    }


    public record UserId(Long userId) {
        public UserId(@NotNull Long userId) {
            if (userId <= 0)
                throw new IllegalArgumentException("User ID must be a positive number.");
            this.userId = userId;
            validate(this);
        }
    }

    public static User userWithId(UserId userId, String username, String email, String password, UserStatus status, Set<Role> roles) {
        return new User(userId, username, email, password, status, roles);
    }

    public static User userWithoutId(String username, String email, String password, UserStatus status, Set<Role> roles) {
        return new User(null, username, email, password, status, roles);
    }

    public Optional<UserId> getUserId() {
        return Optional.ofNullable(this.userId);
    }
}
