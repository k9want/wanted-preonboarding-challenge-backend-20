package com.example.wantedmarket.common.controller;

import com.example.wantedmarket.auth.controller.consts.ErrorCodes;

public record ApiResponse<T>(
    T data,
    ApiErrorResponse error
) {
    public static <T> ApiResponse<T> from(T data) {
        return new ApiResponse<>(data, null);
    }

    public static <T> ApiResponse<T> fromErrorCodes(ErrorCodes errorCodes) {
        final ApiErrorResponse errorResponse = new ApiErrorResponse(errorCodes.message,
            errorCodes.code);
        return new ApiResponse<>(null, errorResponse);
    }
}
