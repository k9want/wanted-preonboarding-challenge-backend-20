package com.example.wantedmarket.product.controller;

import com.example.wantedmarket.common.annotation.TokenByUserId;
import com.example.wantedmarket.common.controller.ApiResponse;
import com.example.wantedmarket.common.exception.WantedMarketHttpException;
import com.example.wantedmarket.product.controller.consts.ProductErrorCode;
import com.example.wantedmarket.product.controller.dto.ListProductResponse;
import com.example.wantedmarket.product.controller.dto.ProductFindByIdResponse;
import com.example.wantedmarket.product.controller.dto.ProductRegisterRequest;
import com.example.wantedmarket.product.controller.dto.ProductRegisterResponse;
import com.example.wantedmarket.product.service.ProductService;
import com.example.wantedmarket.product.service.domain.Product;
import com.example.wantedmarket.product.service.exception.NotAuthorizedProductToRegisterException;
import com.example.wantedmarket.product.service.exception.ProductNotFoundException;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/product")
    public ApiResponse<ProductRegisterResponse> register(
        @TokenByUserId Long userId,
        @Valid @RequestBody ProductRegisterRequest request
    ) {
        final Product result;

        try {
            result = productService.register(userId, request.name(), request.price());
        } catch (NotAuthorizedProductToRegisterException e) {
            throw new WantedMarketHttpException(ProductErrorCode.NOT_AUTHORIZED_PRODUCT_TO_REGISTER,
                HttpStatus.UNAUTHORIZED);
        }

        ProductRegisterResponse response = ProductRegisterResponse.from(result);
        return ApiResponse.fromData(response);
    }

    @GetMapping("/products/{productId}")
    public ApiResponse<ProductFindByIdResponse> findById(@PathVariable Long productId) {
        Product result;
        try {
            result = productService.findById(productId);
        } catch (ProductNotFoundException e) {
            throw new WantedMarketHttpException(ProductErrorCode.PRODUCT_NOT_FOUND,
                HttpStatus.NOT_FOUND);
        }
        ProductFindByIdResponse response = ProductFindByIdResponse.from(result);
        return ApiResponse.fromData(response);
    }

    @GetMapping("/products")
    public ApiResponse<ListProductResponse> findAll() {
        List<Product> result = productService.findAll();
        ListProductResponse response = ListProductResponse.from(result);
        return ApiResponse.fromData(response);
    }
}
