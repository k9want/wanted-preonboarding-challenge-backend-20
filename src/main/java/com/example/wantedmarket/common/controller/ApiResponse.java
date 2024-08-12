package com.example.wantedmarket.common.controller;

import java.util.Map;

public record ApiResponse<T>(
    T data,
    ApiErrorResponse error
) {
    public static <T> ApiResponse<T> fromData(T data) {
        return new ApiResponse<>(data, null);
    }

    public static <T> ApiResponse<T> fromErrorCodes(ErrorCodesIf errorCodesIf) {
        final ApiErrorResponse errorResponse = new ApiErrorResponse(errorCodesIf.getMessage(), errorCodesIf.getCode(), null);
        return new ApiResponse<>(null, errorResponse);
    }

    public static <T> ApiResponse<T> fromErrorCodes(ErrorCodesIf errorCodesIf,  Map<String, String> fieldErrors) {
        final ApiErrorResponse errorResponse = new ApiErrorResponse(errorCodesIf.getMessage(), errorCodesIf.getCode(), fieldErrors);
        return new ApiResponse<>(null, errorResponse);
    }
}
