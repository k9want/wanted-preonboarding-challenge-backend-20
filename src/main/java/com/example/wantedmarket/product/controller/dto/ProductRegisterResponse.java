package com.example.wantedmarket.product.controller.dto;

import com.example.wantedmarket.product.service.domain.Product;

public record ProductRegisterResponse(
    Long productId,
    String name,
    Double price,
    String status
) {

    public static ProductRegisterResponse from(Product product) {
        return new ProductRegisterResponse(
            product.getId(),
            product.getName(),
            product.getPrice(),
            product.getStatus().getDescription()
        );
    }
}
