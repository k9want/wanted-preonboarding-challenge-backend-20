package com.example.wantedmarket.deal.controller.consts;

import com.example.wantedmarket.common.controller.ErrorCodesIf;
import lombok.Getter;

@Getter
public enum DealErrorCode implements ErrorCodesIf {

    NOT_AUTHORIZED_PRODUCT_TO_BUY("회원만 제품을 구매할 수 있습니다.", 3000L),
    TO_PURCHASE_PRODUCT_NOT_FOUND("존재하지 않는 제품입니다.", 3001L),
    STATUS_ALREADY_RESERVED_PRODUCT("이미 거래가 예약된 제품입니다.", 3002L),
    STATUS_ALREADY_COMPLETED_PRODUCT("이미 거래 완료된 제품입니다.", 3003L),
    SELF_PURCHASE_NOT_ALLOWED("본인이 판매하는 제품은 구매할 수 없습니다.", 3004L),
    ;
    public final String message;
    public final Long code;

    DealErrorCode(String message, Long code) {
        this.message = message;
        this.code = code;
    }
}
