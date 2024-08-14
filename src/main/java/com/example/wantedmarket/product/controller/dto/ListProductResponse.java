package com.example.wantedmarket.product.controller.dto;

import com.example.wantedmarket.product.service.domain.Product;
import java.util.List;

public record ListProductResponse(
    List<ProductResponse> products

) {
    public static ListProductResponse from(List<Product> products) {
        return new ListProductResponse(
            products.stream()
                .map(ProductResponse::from)
                .toList()
        );
    }
}
