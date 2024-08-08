package com.example.wantedmarket.user.controller.dto;

import com.example.wantedmarket.user.service.domain.User;

public record UserRegisterResponse(
    String username,
    String nickname
) {

    public static UserRegisterResponse from(User user) {
        return new UserRegisterResponse(user.getUsername(), user.getNickname());
    }
}
