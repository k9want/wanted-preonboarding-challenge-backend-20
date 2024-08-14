package com.example.wantedmarket.product.controller.dto;

import com.example.wantedmarket.product.service.domain.Product;
import com.example.wantedmarket.user.service.domain.User;
import java.util.List;

public record ProductFindAllResponse(
    List<ProductResponse> products

) {
    public static ProductFindAllResponse from(List<Product> products) {
        return new ProductFindAllResponse(
            products.stream()
                .map(ProductResponse::from)
                .toList()
        );
    }
}
