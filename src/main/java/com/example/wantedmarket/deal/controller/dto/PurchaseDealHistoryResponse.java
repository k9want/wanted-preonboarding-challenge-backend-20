package com.example.wantedmarket.deal.controller.dto;

import com.example.wantedmarket.deal.service.domain.Deal;
import java.util.List;

public record PurchaseDealHistoryResponse(
    List<PurchaseDealResponse> purchasesHistory
) {

    public static PurchaseDealHistoryResponse from(List<Deal> deals) {
        return new PurchaseDealHistoryResponse(
            deals.stream()
                .map(PurchaseDealResponse::from)
                .toList()
        );
    }
}
