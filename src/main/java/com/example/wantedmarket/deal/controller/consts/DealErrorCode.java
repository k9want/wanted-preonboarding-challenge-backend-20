package com.example.wantedmarket.deal.controller.consts;

import com.example.wantedmarket.common.controller.ErrorCodesIf;
import lombok.Getter;

@Getter
public enum DealErrorCode implements ErrorCodesIf {

    NOT_AUTHORIZED_PRODUCT_TO_BUY("회원만 제품을 구매할 수 있습니다.", 3000L),
    PRODUCT_NOT_FOUND("존재하지 않는 제품입니다.", 3001L),
    STATUS_ALREADY_RESERVED_PRODUCT("이미 거래가 예약된 제품입니다.", 3002L),
    STATUS_ALREADY_COMPLETED_PRODUCT("이미 거래 완료된 제품입니다.", 3003L),
    SELF_PURCHASE_NOT_ALLOWED("본인이 판매하는 제품은 구매할 수 없습니다.", 3004L),
    NOT_AUTHORIZED_PURCHASES_HISTORY("회원만 제품 구매 목록을 조회할 수 있습니다.", 3005L),
    NOT_AUTHORIZED_SALES_HISTORY("회원만 제품 판매 목록을 조회할 수 있습니다.", 3006L),
    NOT_AUTHORIZED_PRODUCT_DETAIL("회원용 제품 상세 조회입니다.", 3007L),
    DEAL_NOT_FOUND("존재하지 않는 거래입니다.", 3008L),
    NOT_SELLER_OF_PRODUCT("본인의 판매제품이 아닙니다.", 3009L),
    STATUS_NOT_RESERVATION("예약 중인 제품이 아닙니다.", 3010L),
    NOT_AUTHORIZED_PRODUCT_TO_APPROVE_SELL("회원만 제품을 판매 승인할 수 있습니다.", 3011L),

    ;
    public final String message;
    public final Long code;

    DealErrorCode(String message, Long code) {
        this.message = message;
        this.code = code;
    }
}
