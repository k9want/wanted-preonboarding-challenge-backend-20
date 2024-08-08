package com.example.wantedmarket.auth.controller.consts;

public enum ErrorCodes {

    ;

    public final String message;
    public final Long code;

    ErrorCodes(String message, Long code) {
        this.message = message;
        this.code = code;
    }
}
