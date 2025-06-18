package com.devsxplore.authservice.adapter.in.web.dto;

import com.devsxplore.authservice.domain.util.UserStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserUpdateDto {

    @NotNull
    private Long userId;

    @NotBlank
    @Size(min = 3, max = 25)
    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotNull
    private UserStatus status;
}
