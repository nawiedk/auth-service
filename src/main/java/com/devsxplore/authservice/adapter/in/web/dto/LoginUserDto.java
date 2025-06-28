package com.devsxplore.authservice.adapter.in.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginUserDto {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;
}
