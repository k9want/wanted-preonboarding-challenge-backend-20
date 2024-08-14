package com.example.wantedmarket.user.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record UserRegisterRequest(
    @NotBlank
    String username,
    @NotBlank
    String password,
    @NotBlank
    String nickname
) {

}
