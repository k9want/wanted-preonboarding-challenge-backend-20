package com.example.wantedmarket.product.controller.dto;

import com.example.wantedmarket.user.service.domain.User;

public record SellerResponse(
    String nickname
) {
    public static SellerResponse from(User seller) {
        return new SellerResponse(
            seller.getNickname()
        );
    }
}
