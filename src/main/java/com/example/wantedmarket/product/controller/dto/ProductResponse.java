package com.example.wantedmarket.product.controller.dto;

import com.example.wantedmarket.product.service.domain.Product;

public record ProductResponse(
    Long id,
    String name,
    Double price,
    SellerResponse seller,
    String status
) {
    public static ProductResponse from(Product product) {
        return new ProductResponse(
            product.getId(),
            product.getName(),
            product.getPrice(),
            SellerResponse.from(product.getSeller()),
            product.getStatus().getDescription()
        );
    }
}
