package com.example.wantedmarket.common.controller.consts;

import com.example.wantedmarket.common.controller.ErrorCodesIf;
import lombok.Getter;

@Getter
public enum CommonErrorCodes implements ErrorCodesIf {
    BAD_REQUEST_JSON_PARSE_ERROR("BAD_REQUEST - JSON 파싱 예외 발생", 400L),
    BAD_REQUEST_INVALID_INPUT_ERROR("BAD_REQUEST - 유효하지 않은 입력값 발생", 401L),
    BAD_REQUEST_REQUEST_ATTRIBUTES_MISSING("BAD_REQUEST - RequestAttributes is null, Cannot resolve userId.", 402L),
    BAD_REQUEST_USER_ID_MISSING("BAD_REQUEST - userId attribute is not set in the RequestAttributes.", 403L),
    UNAUTHORIZED_TOKEN_AUTHORIZATION_TOKEN_NOT_FOUND("UNAUTHORIZED - 인증 헤더 토큰 없음.", 404L),
    UNAUTHORIZED_TOKEN_EXPIRED_TOKEN("UNAUTHORIZED - 만료된 인증 헤더 토큰", 405L),
    BAD_REQUEST_TOKEN_INVALID_TOKEN("BAD_REQUEST - 유효하지 않은 인증 헤더 토큰.", 406L),
    INTERNAL_SERVER_ERROR_TOKEN_AUTHORIZATION_FAIL("INTERNAL_SERVER_ERROR - 인증 헤더 토큰 인증 실패.", 407L),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR - 예기치 못한 예외 발생", 500L),

    ;

    public final String message;
    public final Long code;

    CommonErrorCodes(String message, Long code) {
        this.message = message;
        this.code = code;
    }
}
