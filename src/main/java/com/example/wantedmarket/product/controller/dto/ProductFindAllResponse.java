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

    public static record ProductResponse(
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

    public static record SellerResponse(
        String nickname
    ) {

        public static SellerResponse from(User seller) {
            return new SellerResponse(
                seller.getNickname()
            );
        }
    }
}
