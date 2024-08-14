package com.example.wantedmarket.product.controller.dto;

import com.example.wantedmarket.product.service.domain.Product;

public record ProductFindByIdResponse(
    ProductResponse product
) {

    public static ProductFindByIdResponse from(Product product) {
        return new ProductFindByIdResponse(
            ProductResponse.from(product)
        );
    }
}
