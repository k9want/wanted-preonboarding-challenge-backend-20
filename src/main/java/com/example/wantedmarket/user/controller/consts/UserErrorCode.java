package com.example.wantedmarket.user.controller.consts;

import com.example.wantedmarket.common.controller.ErrorCodesIf;
import lombok.Getter;

@Getter
public enum UserErrorCode implements ErrorCodesIf {

    LOGIN_USER_NOT_FOUND("존재하지 않는 사용자입니다. 이메일과 비밀번호를 확인해주세요", 1000L),

    ;

    public final String message;
    public final Long code;

    UserErrorCode(String message, Long code) {
        this.message = message;
        this.code = code;
    }
}
