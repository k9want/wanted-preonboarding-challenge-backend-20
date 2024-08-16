package com.example.wantedmarket.deal.controller.dto;

import com.example.wantedmarket.product.service.domain.Product;
import java.util.List;

public record SalesNowResponse(
    List<DealProductResponse> salesNow
) {

    public static SalesNowResponse from(List<Product> salesProducts) {
        return new SalesNowResponse(
            salesProducts.stream()
                .map(DealProductResponse::from)
                .toList()
        );
    }

}
