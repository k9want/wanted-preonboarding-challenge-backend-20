package com.example.wantedmarket.common.controller;

public record ApiErrorResponse(
    String message,
    Long code
) {

}
