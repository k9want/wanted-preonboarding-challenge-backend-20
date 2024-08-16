package com.example.wantedmarket.product.controller.dto;

import com.example.wantedmarket.product.service.domain.Product;

public record ProductDetailResponse(
    Long productId,
    String name,
    Double price,
    UserResponse seller,
    String status
) {
    public static ProductDetailResponse from(Product product) {
        return new ProductDetailResponse(
            product.getId(),
            product.getName(),
            product.getPrice(),
            UserResponse.from(product.getSeller()),
            product.getStatus().getDescription()
        );
    }
}
