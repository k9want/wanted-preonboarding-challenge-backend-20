package com.example.wantedmarket.user.controller.consts;

import com.example.wantedmarket.common.controller.ErrorCodesIf;
import lombok.Getter;

@Getter
public enum UserErrorCode implements ErrorCodesIf {

    DUPLICATE_USERNAME("아이디가 이미 존재합니다.", 1000L),
    AUTH_USERNAME_NOT_FOUND("아이디가 존재하지 않습니다.", 1010L),
    AUTH_INVALID_PASSWORD("비밀번호가 올바르지 않습니다.", 1011L),

    ;

    public final String message;
    public final Long code;

    UserErrorCode(String message, Long code) {
        this.message = message;
        this.code = code;
    }
}
