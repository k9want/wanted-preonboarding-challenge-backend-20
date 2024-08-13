package com.example.wantedmarket.product.controller.dto;

import com.example.wantedmarket.product.repository.jpa.entity.enums.ProductStatus;
import com.example.wantedmarket.product.service.domain.Product;

public record ProductRegisterResponse(
    String name,
    Double price,
    String status
) {

    public static ProductRegisterResponse from(Product product) {
        return new ProductRegisterResponse(
            product.getName(),
            product.getPrice(),
            product.getStatus().getDescription()
        );
    }
}
