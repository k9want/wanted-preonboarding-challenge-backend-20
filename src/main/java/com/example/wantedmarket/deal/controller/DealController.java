package com.example.wantedmarket.deal.controller;

import com.example.wantedmarket.common.annotation.TokenByUserId;
import com.example.wantedmarket.common.controller.ApiResponse;
import com.example.wantedmarket.common.exception.WantedMarketHttpException;
import com.example.wantedmarket.deal.controller.consts.DealErrorCode;
import com.example.wantedmarket.deal.controller.dto.PurchaseResponse;
import com.example.wantedmarket.deal.controller.dto.ListPurchaseHistoryResponse;
import com.example.wantedmarket.deal.service.DealService;
import com.example.wantedmarket.deal.service.domain.Deal;
import com.example.wantedmarket.deal.service.exception.NotAuthorizedProductToBuyException;
import com.example.wantedmarket.deal.service.exception.SelfPurchaseNotAllowedException;
import com.example.wantedmarket.deal.service.exception.ToPurchaseProductNotFoundException;
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
        } catch (NotAuthorizedProductToBuyException e) {
            throw new WantedMarketHttpException(DealErrorCode.NOT_AUTHORIZED_PRODUCT_TO_BUY, HttpStatus.UNAUTHORIZED);
        } catch (ToPurchaseProductNotFoundException e) {
            throw new WantedMarketHttpException(DealErrorCode.TO_PURCHASE_PRODUCT_NOT_FOUND, HttpStatus.NOT_FOUND);
        } catch (SelfPurchaseNotAllowedException e) {
            throw new WantedMarketHttpException(DealErrorCode.SELF_PURCHASE_NOT_ALLOWED, HttpStatus.CONFLICT);
        } catch (StatusAlreadyReservedProductException e) {
            throw new WantedMarketHttpException(DealErrorCode.STATUS_ALREADY_RESERVED_PRODUCT, HttpStatus.CONFLICT);
        } catch (StatusAlreadyCompletedProductException e) {
            throw new WantedMarketHttpException(DealErrorCode.STATUS_ALREADY_COMPLETED_PRODUCT, HttpStatus.CONFLICT);
        }

        PurchaseResponse response = PurchaseResponse.from(result);
        return ApiResponse.fromData(response);
    }

    @GetMapping("/purchases-history")
    public ApiResponse<ListPurchaseHistoryResponse> purchasesHistory(
        @TokenByUserId Long userId
    ) {
        List<Deal> result;
        try {
            result = dealService.findPurchasesHistoryByUserId(userId);
        } catch (NotAuthorizedProductToBuyException e) {
            throw new WantedMarketHttpException(DealErrorCode.NOT_AUTHORIZED_PRODUCT_TO_BUY, HttpStatus.UNAUTHORIZED);
        }
        ListPurchaseHistoryResponse response = ListPurchaseHistoryResponse.from(result);
        return ApiResponse.fromData(response);
    }
}
