package com.example.wantedmarket.common.controller;

import com.example.wantedmarket.common.controller.consts.CommonErrorCodes;
import com.example.wantedmarket.common.exception.WantedMarketHttpException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidExceptionHandler(MethodArgumentNotValidException ex) {

        Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
            .collect(Collectors.toMap(
                fieldError -> camelToSnake(fieldError.getField()),
                FieldError::getDefaultMessage
            ));

        final ApiResponse<Object> body = ApiResponse.fromErrorCodes(CommonErrorCodes.BAD_REQUEST_INVALID_INPUT_ERROR, errors);
        final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .contentType(contentType)
            .body(body);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Object>> handleJsonParseExceptionHandler(HttpMessageNotReadableException e) {

        final ApiResponse<Object> body = ApiResponse.fromErrorCodes(CommonErrorCodes.BAD_REQUEST_JSON_PARSE_ERROR);
        final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .contentType(contentType)
            .body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleExceptionHandler(Exception e) {

        log.error("{e}", e);

        final ApiResponse<Object> body = ApiResponse.fromErrorCodes(CommonErrorCodes.INTERNAL_SERVER_ERROR);
        final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON, StandardCharsets.UTF_8);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .contentType(contentType)
            .body(body);
    }

    private String camelToSnake(String str) {
        return str.replaceAll("([a-z])([A-Z]+)", "$1_$2").toLowerCase();
    }
}
