package com.example.wantedmarket.product.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRegisterRequest(
    @NotBlank
    String name,

    @NotNull
    Double price
) {

}
