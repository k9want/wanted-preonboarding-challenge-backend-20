package com.example.wantedmarket.deal.controller.dto;

import com.example.wantedmarket.deal.service.domain.Deal;
import java.util.List;

public record SaleDealHistoryResponse(
    List<SaleDealResponse> salesHistory
) {

    public static SaleDealHistoryResponse from(List<Deal> deals) {
        return new SaleDealHistoryResponse(
            deals.stream()
                .map(SaleDealResponse::from)
                .toList()
        );
    }

}
