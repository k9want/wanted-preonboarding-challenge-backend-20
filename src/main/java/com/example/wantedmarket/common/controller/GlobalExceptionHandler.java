package com.example.wantedmarket.common.controller;

import com.example.wantedmarket.common.exception.WantedMarketHttpException;
import java.nio.charset.StandardCharsets;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {WantedMarketHttpException.class})
    public ResponseEntity<ApiResponse<Void>> handleWantedMarketHttpException(
        WantedMarketHttpException exception
    ) {
        ApiResponse<Void> body = ApiResponse.fromErrorCodes(exception.getErrorCodes());
        MediaType contentType = new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8);
        HttpStatus status = exception.getHttpStatus();

        return ResponseEntity.status(status)
            .contentType(contentType)
            .body(body);

    }

}
