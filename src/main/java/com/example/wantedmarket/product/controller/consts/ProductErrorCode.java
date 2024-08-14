package com.example.wantedmarket.product.controller.consts;

import com.example.wantedmarket.common.controller.ErrorCodesIf;
import lombok.Getter;

@Getter
public enum ProductErrorCode implements ErrorCodesIf {

    NOT_AUTHORIZED_PRODUCT_TO_REGISTER("회원만 제품을 등록할 수 있습니다.", 2000L),
    PRODUCT_NOT_FOUND("존재하지 않는 제품입니다.", 2001L),
    PRODUCT_FOR_USER_NOT_FOUND("해당 기능은 회원만 가능합니다.", 2002L)

    ;
    public final String message;
    public final Long code;

    ProductErrorCode(String message, Long code) {
        this.message = message;
        this.code = code;
    }


}
