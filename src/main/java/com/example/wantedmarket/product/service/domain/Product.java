package com.example.wantedmarket.product.service.domain;

import com.example.wantedmarket.product.repository.jpa.entity.enums.ProductStatus;
import com.example.wantedmarket.user.service.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Product {
    private final Long id;
    private final String name;
    private final Double price;
    private final User seller;
    private final ProductStatus status;

    @Builder
    public Product(Long id, String name, Double price, User seller, ProductStatus status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.seller = seller;
        this.status = status;
    }

    public static Product register(String name, Double price, User seller) {
        return Product.builder()
            .name(name)
            .price(price)
            .seller(seller)
            .status(ProductStatus.ON_SALE)
            .build();
    }
}
