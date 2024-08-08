package com.example.wantedmarket.user.controller.consts;

import com.example.wantedmarket.common.controller.ErrorCodesIf;
import lombok.Getter;

@Getter
public enum ErrorCodes implements ErrorCodesIf {

    INVALID_INPUT_USERNAME("유저 아이디는 빈 값이거나 공백일 수 없습니다.", 2000L),
    INVALID_INPUT_PASSWORD("비밀번호는 빈 값이거나 공백일 수 없습니다.", 2001L),
    INVALID_INPUT_NICKNAME("닉네임은 빈 값이거나 공백일 수 없습니다.", 2002L),

    ;

    public final String message;
    public final Long code;

    ErrorCodes(String message, Long code) {
        this.message = message;
        this.code = code;
    }
}
