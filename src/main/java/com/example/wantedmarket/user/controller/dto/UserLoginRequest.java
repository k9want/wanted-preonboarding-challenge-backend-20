package com.example.wantedmarket.user.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record UserLoginRequest(
    @NotBlank
    String username,
    @NotBlank
    String password
) {

}
