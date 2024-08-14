package com.example.wantedmarket.product.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRegisterRequest(
    @NotBlank(message = "{productRegisterRequest.name.notBlank}")
    String name,

    @NotNull(message = "{productRegisterRequest.price.notNull}")
    Double price
) {

}
