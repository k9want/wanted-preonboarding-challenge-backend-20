package com.example.wantedmarket.deal.controller.dto;

import com.example.wantedmarket.deal.service.domain.Deal;

public record ProductPurchaseResponse(
    String dealStatus
) {

    public static ProductPurchaseResponse from(Deal deal) {
        return new ProductPurchaseResponse(
            deal.getStatus().getDescription()
        );
    }
}
