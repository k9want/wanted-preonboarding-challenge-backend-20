package com.example.wantedmarket.common.exception;

import com.example.wantedmarket.user.controller.consts.ErrorCodes;
import org.springframework.http.HttpStatus;

public class WantedMarketHttpException extends RuntimeException {

    private final ErrorCodes errorCodes;
    private final HttpStatus httpStatus;

    public WantedMarketHttpException(ErrorCodes errorCodes, HttpStatus httpStatus) {
        this.errorCodes = errorCodes;
        this.httpStatus = httpStatus;
    }

    public ErrorCodes getErrorCodes() {
        return errorCodes;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
