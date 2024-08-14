package com.example.wantedmarket.product.controller.dto;

import com.example.wantedmarket.user.service.domain.User;

public record UserResponse(
    String nickname
) {
    public static UserResponse from(User seller) {
        return new UserResponse(
            seller.getNickname()
        );
    }
}
