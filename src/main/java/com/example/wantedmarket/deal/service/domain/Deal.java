package com.example.wantedmarket.deal.service.domain;

import com.example.wantedmarket.deal.service.domain.enums.DealStatus;
import com.example.wantedmarket.deal.service.exception.SelfPurchaseNotAllowedException;
import com.example.wantedmarket.product.service.domain.Product;
import com.example.wantedmarket.deal.service.exception.NotSellerOfProductException;
import com.example.wantedmarket.user.service.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Deal {
    private final Long id;
    private final User buyer;
    private final User seller;
    private final Product product;
    private final DealStatus status;

    @Builder
    public Deal(Long id, User buyer, User seller, Product product, DealStatus status) {
        this.id = id;
        this.buyer = buyer;
        this.seller = seller;
        this.product = product;
        this.status = status;
    }

    // 거래 생성
    public static Deal create(User buyer, Product product) {
        // 본인이 판매하는 제품 구매 시도
        if (buyer.getId().equals(product.getSeller().getId())) {
            throw new SelfPurchaseNotAllowedException();
        }

        Product updateProduct = product.reserve();

        return Deal.builder()
            .buyer(buyer)
            .seller(product.getSeller())
            .product(updateProduct)
            .status(DealStatus.PENDING)
            .build();
    }

    // 거래 승인 (판매 승인 처리)
    public Deal approveSale(User seller) {
        // 본인의 판매제품이 아닌데 판매승인 시도
        if (!seller.getId().equals(this.seller.getId())) {
            throw new NotSellerOfProductException();
        }

        // 제품을 거래 완료 상태로 변경
        Product updateProduct = product.completeSale();

        return Deal.builder()
            .id(this.id)
            .buyer(this.buyer)
            .seller(this.seller)
            .product(updateProduct)
            .status(DealStatus.APPROVED)
            .build();
    }
}
