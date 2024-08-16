package com.example.wantedmarket.deal.controller.dto;

import com.example.wantedmarket.deal.service.command.ProductDetailForUserCommand;
import com.example.wantedmarket.product.controller.dto.UserResponse;
import java.util.ArrayList;
import java.util.List;

public record ProductDetailForUserResponse(
    Long productId,
    String name,
    Double price,
    UserResponse seller,
    String status,
    PurchaseDealResponse purchaseDeal,
    List<SaleDealResponse> salesHistory
) {
    public static ProductDetailForUserResponse from(ProductDetailForUserCommand command) {
        UserResponse seller = null;
        PurchaseDealResponse purchaseDeal = null;
        List<SaleDealResponse> salesHistory = new ArrayList<>();

        if (command.product().getSeller() != null) {
            seller = UserResponse.from(command.product().getSeller());
        }

        if (command.purchaseDeal() != null) {
            purchaseDeal = PurchaseDealResponse.from(command.purchaseDeal());
        }

        if (command.salesDeals() != null && !command.salesDeals().isEmpty()) {
            salesHistory = command.salesDeals().stream()
                .map(SaleDealResponse::from)
                .toList();
        }

        return new ProductDetailForUserResponse(
            command.product().getId(),
            command.product().getName(),
            command.product().getPrice(),
            seller,
            command.product().getStatus().getDescription(),
            purchaseDeal,
            salesHistory
        );
    }
}
