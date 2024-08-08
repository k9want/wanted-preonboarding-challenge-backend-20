package com.example.wantedmarket.common.controller;

import java.util.Map;

public record ApiErrorResponse(
    String message,
    Long code,
    Map<String, String> fieldErrors

) {

}
