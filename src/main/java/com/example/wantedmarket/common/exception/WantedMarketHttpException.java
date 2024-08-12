package com.example.wantedmarket.common.exception;

import com.example.wantedmarket.common.controller.ErrorCodesIf;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

public class WantedMarketHttpException extends RuntimeException {

    private final ErrorCodesIf errorCodesIf;
    private final HttpStatus httpStatus;

    public WantedMarketHttpException(ErrorCodesIf errorCodesIf, HttpStatus httpStatus) {
        this.errorCodesIf = errorCodesIf;
        this.httpStatus = httpStatus;
    }

    public ErrorCodesIf getErrorCodes() {
        return errorCodesIf;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
