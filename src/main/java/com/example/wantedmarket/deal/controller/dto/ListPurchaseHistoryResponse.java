package com.example.wantedmarket.deal.controller.dto;

import com.example.wantedmarket.deal.service.domain.Deal;
import com.example.wantedmarket.product.controller.dto.UserResponse;
import java.util.List;

public record ListPurchaseHistoryResponse(
    List<PurchaseHistoryResponse> purchasesHistory
) {

    public static ListPurchaseHistoryResponse from(List<Deal> deals) {
        return new ListPurchaseHistoryResponse(
            deals.stream()
                .map(PurchaseHistoryResponse::from)
                .toList()
        );
    }

    public record PurchaseHistoryResponse(
        DealProductResponse product,
        UserResponse seller,
        String dealStatus
    ) {

        public static PurchaseHistoryResponse from(Deal deal) {
            return new PurchaseHistoryResponse(
                DealProductResponse.from(deal.getProduct()),
                UserResponse.from(deal.getSeller()),
                deal.getStatus().getDescription()
            );
        }
    }

}
