package com.devsxplore.authservice.adapter.in.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCreateDto {

    @NotBlank
    @Size(min = 3, max = 25)
    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

}
