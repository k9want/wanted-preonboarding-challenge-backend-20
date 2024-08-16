package com.example.wantedmarket.deal.controller.dto;

import com.example.wantedmarket.deal.service.domain.Deal;

public record PurchaseResponse(
    Long productId,
    String productStatus,
    String dealStatus
) {

    public static PurchaseResponse from(Deal deal) {
        return new PurchaseResponse(
            deal.getId(),
            deal.getProduct().getStatus().getDescription(),
            deal.getStatus().getDescription()
        );
    }
}
