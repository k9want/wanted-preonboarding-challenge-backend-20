package com.example.wantedmarket.deal.controller.dto;

import com.example.wantedmarket.deal.service.domain.Deal;
import com.example.wantedmarket.product.controller.dto.UserResponse;

public record SaleDealResponse(
    Long dealId,
    DealProductResponse product,
    UserResponse buyer,
    String dealStatus
) {

    public static SaleDealResponse from(Deal deal) {
        return new SaleDealResponse(
            deal.getId(),
            DealProductResponse.from(deal.getProduct()),
            UserResponse.from(deal.getBuyer()),
            deal.getStatus().getDescription()
        );
    }
}
