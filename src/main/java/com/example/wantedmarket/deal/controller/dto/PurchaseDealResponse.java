package com.example.wantedmarket.deal.controller.dto;

import com.example.wantedmarket.deal.service.domain.Deal;
import com.example.wantedmarket.product.controller.dto.UserResponse;

public record PurchaseDealResponse(
    DealProductResponse product,
    UserResponse seller,
    String dealStatus
) {

    public static PurchaseDealResponse from(Deal deal) {
        return new PurchaseDealResponse(
            DealProductResponse.from(deal.getProduct()),
            UserResponse.from(deal.getSeller()),
            deal.getStatus().getDescription()
        );
    }
}
