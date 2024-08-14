package com.example.wantedmarket.deal.controller;

import com.example.wantedmarket.common.annotation.TokenByUserId;
import com.example.wantedmarket.common.controller.ApiResponse;
import com.example.wantedmarket.common.exception.WantedMarketHttpException;
import com.example.wantedmarket.deal.controller.consts.DealErrorCode;
import com.example.wantedmarket.deal.controller.dto.ProductDetailForUserResponse;
import com.example.wantedmarket.deal.controller.dto.PurchaseDealHistoryResponse;
import com.example.wantedmarket.deal.controller.dto.SaleDealHistoryResponse;
import com.example.wantedmarket.deal.controller.dto.PurchaseResponse;
import com.example.wantedmarket.deal.service.DealService;
import com.example.wantedmarket.deal.service.command.ProductDetailForUserCommand;
import com.example.wantedmarket.deal.service.domain.Deal;
import com.example.wantedmarket.deal.service.exception.NotAuthorizedDealToProductException;
import com.example.wantedmarket.deal.service.exception.ProductNotFoundException;
import com.example.wantedmarket.deal.service.exception.SelfPurchaseNotAllowedException;
import com.example.wantedmarket.product.service.exception.StatusAlreadyCompletedProductException;
import com.example.wantedmarket.product.service.exception.StatusAlreadyReservedProductException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DealController {

    private final DealService dealService;

    /*
     * 상품 구매
     * */
    @PostMapping("/products/{productId}/purchase")
    public ApiResponse<PurchaseResponse> purchase(
        @TokenByUserId Long userId,
        @PathVariable(name = "productId") Long productId
    ) {

        Deal result;
        try {
            result = dealService.createDeal(userId, productId);
        } catch (NotAuthorizedDealToProductException e) {
            throw new WantedMarketHttpException(DealErrorCode.NOT_AUTHORIZED_PRODUCT_TO_BUY,
                HttpStatus.UNAUTHORIZED);
        } catch (ProductNotFoundException e) {
            throw new WantedMarketHttpException(DealErrorCode.PRODUCT_NOT_FOUND,
                HttpStatus.NOT_FOUND);
        } catch (SelfPurchaseNotAllowedException e) {
            throw new WantedMarketHttpException(DealErrorCode.SELF_PURCHASE_NOT_ALLOWED,
                HttpStatus.CONFLICT);
        } catch (StatusAlreadyReservedProductException e) {
            throw new WantedMarketHttpException(DealErrorCode.STATUS_ALREADY_RESERVED_PRODUCT,
                HttpStatus.CONFLICT);
        } catch (StatusAlreadyCompletedProductException e) {
            throw new WantedMarketHttpException(DealErrorCode.STATUS_ALREADY_COMPLETED_PRODUCT,
                HttpStatus.CONFLICT);
        }

        PurchaseResponse response = PurchaseResponse.from(result);
        return ApiResponse.fromData(response);
    }

    /*
     * 회원 - 제품 상세 조회
     * 본인의 구매 혹은 판매 제품은 거래내역 포함
     * */
    @GetMapping("/products/{productId}")
    public ApiResponse<ProductDetailForUserResponse> findProductDetailForUser(
        @TokenByUserId Long userId,
        @PathVariable Long productId) {
        ProductDetailForUserCommand result;
        try {
            result = dealService.findByIdForUser(userId, productId);
        } catch (NotAuthorizedDealToProductException e) {
            throw new WantedMarketHttpException(DealErrorCode.NOT_AUTHORIZED_PRODUCT_DETAIL,
                HttpStatus.UNAUTHORIZED);
        } catch (ProductNotFoundException e) {
            throw new WantedMarketHttpException(DealErrorCode.PRODUCT_NOT_FOUND,
                HttpStatus.NOT_FOUND);
        }
        ProductDetailForUserResponse response = ProductDetailForUserResponse.from(result);
        return ApiResponse.fromData(response);
    }

    /*
     * 구매 내역 조회
     * */
    @GetMapping("/purchases-history")
    public ApiResponse<PurchaseDealHistoryResponse> purchasesHistory(
        @TokenByUserId Long userId
    ) {
        List<Deal> result;
        try {
            result = dealService.findPurchasesHistoryByUserId(userId);
        } catch (NotAuthorizedDealToProductException e) {
            throw new WantedMarketHttpException(DealErrorCode.NOT_AUTHORIZED_PURCHASES_HISTORY,
                HttpStatus.UNAUTHORIZED);
        }
        PurchaseDealHistoryResponse response = PurchaseDealHistoryResponse.from(result);
        return ApiResponse.fromData(response);
    }

    /*
     * 판매 내역 조회
     * */
    @GetMapping("/sales-history")
    public ApiResponse<SaleDealHistoryResponse> salesHistory(
        @TokenByUserId Long userId
    ) {
        List<Deal> result;
        try {
            result = dealService.findSalesHistoryByUserId(userId);
        } catch (NotAuthorizedDealToProductException e) {
            throw new WantedMarketHttpException(DealErrorCode.NOT_AUTHORIZED_SALES_HISTORY,
                HttpStatus.UNAUTHORIZED);
        }
        SaleDealHistoryResponse response = SaleDealHistoryResponse.from(result);
        return ApiResponse.fromData(response);
    }
}
