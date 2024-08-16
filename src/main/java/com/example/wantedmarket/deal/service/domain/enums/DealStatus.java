package com.example.wantedmarket.deal.service.domain.enums;

public enum DealStatus {
    PENDING("거래 대기"),
    APPROVED("판매 승인"),
    CANCELLED("거래 취소"), // 만약 취소 기능을 추가할 경우)
    ;

    private final String description;

    DealStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
