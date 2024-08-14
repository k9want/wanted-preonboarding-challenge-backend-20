package com.example.wantedmarket.deal.controller.dto;

import com.example.wantedmarket.deal.service.domain.Deal;
import com.example.wantedmarket.product.controller.dto.UserResponse;
import java.util.List;

public record ListSaleHistoryResponse(
    List<SaleHistoryResponse> salesHistory
) {

    public static ListSaleHistoryResponse from(List<Deal> deals) {
        return new ListSaleHistoryResponse(
            deals.stream()
                .map(SaleHistoryResponse::from)
                .toList()
        );
    }

    public record SaleHistoryResponse(
        DealProductResponse product,
        UserResponse buyer,
        String dealStatus
    ) {

        public static SaleHistoryResponse from(Deal deal) {
            return new SaleHistoryResponse(
                DealProductResponse.from(deal.getProduct()),
                UserResponse.from(deal.getBuyer()),
                deal.getStatus().getDescription()
            );
        }
    }

}
