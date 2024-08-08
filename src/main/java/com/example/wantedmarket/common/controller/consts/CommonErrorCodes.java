package com.example.wantedmarket.common.controller.consts;

import com.example.wantedmarket.common.controller.ErrorCodesIf;
import lombok.Getter;

@Getter
public enum CommonErrorCodes implements ErrorCodesIf {
    BAD_REQUEST_JSON_PARSE_ERROR("BAD_REQUEST - JSON 파싱 예외 발생", 400L),
    BAD_REQUEST_INVALID_INPUT_ERROR("BAD_REQUEST - 유효하지 않은 입력값 발생", 401L),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR - 예기치 못한 예외 발생", 500L),
    ;

    public final String message;
    public final Long code;

    CommonErrorCodes(String message, Long code) {
        this.message = message;
        this.code = code;
    }
}
