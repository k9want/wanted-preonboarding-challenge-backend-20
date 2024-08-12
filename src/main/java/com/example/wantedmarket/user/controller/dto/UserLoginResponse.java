package com.example.wantedmarket.user.controller.dto;

import com.example.wantedmarket.user.service.domain.User;

public record UserLoginResponse(
    String nickname
) {

    public static UserLoginResponse from(User user) {
        return new UserLoginResponse(user.getNickname());
    }
}
