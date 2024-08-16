package com.example.wantedmarket.deal.controller.dto;

import com.example.wantedmarket.deal.service.domain.Deal;

public record ApproveSaleResponse(
    Long dealId,
    String productStatus,
    String dealStatus
) {

    public static ApproveSaleResponse from(Deal deal) {
        return new ApproveSaleResponse(
            deal.getId(),
            deal.getProduct().getStatus().getDescription(),
            deal.getStatus().getDescription()
        );
    }
}
