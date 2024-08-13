package com.example.wantedmarket.product.repository.jpa.entity.enums;

public enum ProductStatus {
    ON_SALE("판매중"),
    RESERVATION("예약중"),
    COMPLETED("완료");

    private final String description;

    ProductStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
