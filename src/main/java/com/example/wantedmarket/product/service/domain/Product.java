package com.example.wantedmarket.product.service.domain;

import com.example.wantedmarket.product.service.domain.enums.ProductStatus;
import com.example.wantedmarket.product.service.exception.StatusAlreadyCompletedProductException;
import com.example.wantedmarket.product.service.exception.StatusAlreadyReservedProductException;
import com.example.wantedmarket.product.service.exception.StatusNotReservationException;
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


    // 제품 예약 시 제품 상태를 예약 중으로 변경
    public Product reserve() {
        //  이미 예약중인 제품 구매 시도
        if (this.status == ProductStatus.RESERVATION) {
            throw new StatusAlreadyReservedProductException();
        }
        // 완료된 제품을 구매 시도
        if (this.status == ProductStatus.COMPLETED) {
            throw new StatusAlreadyCompletedProductException();
        }

        return Product.builder()
            .id(this.id)
            .name(this.name)
            .price(this.price)
            .seller(this.seller)
            .status(ProductStatus.RESERVATION)
            .build();
    }


    // 판매 승인 시 제품 상태를 완료로 변경
    public Product completeSale() {
        // 판매 중인 제품을 판매 승인 시도
        if (this.status == ProductStatus.ON_SALE) {
            throw new StatusNotReservationException();
        }
        // 이미 완료된 재품을 판매 승인 시도
        if (this.status == ProductStatus.COMPLETED) {
            throw new StatusAlreadyCompletedProductException();
        }

        return Product.builder()
            .id(this.id)
            .name(this.name)
            .price(this.price)
            .seller(this.seller)
            .status(ProductStatus.COMPLETED)
            .build();
    }
}
