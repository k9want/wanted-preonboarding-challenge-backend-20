package com.example.wantedmarket.deal.service.domain;

import com.example.wantedmarket.deal.service.domain.enums.DealStatus;
import com.example.wantedmarket.deal.service.exception.SelfPurchaseNotAllowedException;
import com.example.wantedmarket.product.service.domain.Product;
import com.example.wantedmarket.user.service.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Deal {
    private final Long id;
    private final User buyer;
    private final User seller;
    private final Product product;
    private final DealStatus status;

    @Builder
    public Deal(Long id, User buyer, User seller, Product product, DealStatus status) {
        this.id = id;
        this.buyer = buyer;
        this.seller = seller;
        this.product = product;
        this.status = status;
    }

    public static Deal create(User buyer, Product product) {
        return Deal.builder()
            .buyer(buyer)
            .seller(product.getSeller())
            .product(product)
            .status(DealStatus.PENDING)
            .build();
    }
}
