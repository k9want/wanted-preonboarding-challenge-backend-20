package com.example.wantedmarket.deal.controller.dto;

import com.example.wantedmarket.deal.service.domain.Deal;

public record PurchaseResponse(
    String dealStatus
) {

    public static PurchaseResponse from(Deal deal) {
        return new PurchaseResponse(
            deal.getStatus().getDescription()
        );
    }
}
