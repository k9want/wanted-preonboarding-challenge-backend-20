package com.example.wantedmarket.deal.controller.dto;

import com.example.wantedmarket.product.service.domain.Product;

public record DealProductResponse(
    Long productId,
    String name,
    Double price,
    String status
) {

    public static DealProductResponse from(Product product) {
        return new DealProductResponse(
            product.getId(),
            product.getName(),
            product.getPrice(),
            product.getStatus().getDescription()
        );
    }

}
