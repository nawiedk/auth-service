package com.devsxplore.authservice.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RoleCreateDto {

    @NotBlank
    private String name;
}
