package com.example.wantedmarket.deal.service;

import com.example.wantedmarket.deal.service.domain.enums.DealStatus;
import com.example.wantedmarket.deal.service.exception.DealNotFoundException;
import com.example.wantedmarket.deal.repository.DealRepository;
import com.example.wantedmarket.deal.repository.jpa.entity.DealEntity;
import com.example.wantedmarket.deal.service.command.ProductDetailForUserCommand;
import com.example.wantedmarket.deal.service.domain.Deal;
import com.example.wantedmarket.deal.service.exception.NotAuthorizedDealToProductException;
import com.example.wantedmarket.deal.service.exception.ProductNotFoundException;
import com.example.wantedmarket.product.repository.jpa.entity.ProductEntity;
import com.example.wantedmarket.product.repository.jpa.entity.ProductRepository;
import com.example.wantedmarket.product.service.domain.Product;
import com.example.wantedmarket.product.service.domain.enums.ProductStatus;
import com.example.wantedmarket.user.repository.jpa.UserRepository;
import com.example.wantedmarket.user.service.domain.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DealService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final DealRepository dealRepository;

    @Transactional
    // todo: 동시성 이슈 발생하는 기능
    public Deal createDeal(Long userId, Long productId) {
        User buyer = getUserById(userId);
        Product product = getProductById(productId);

        // 거래 생성 (feat.제품 상태: "예약 중" 변경, 거래 상태: "거래 대기" [default])
        Deal createDeal = Deal.create(buyer, product);

        // save
        Product reserveProduct = createDeal.getProduct();
        productRepository.save(ProductEntity.from(reserveProduct));
        return dealRepository.save(DealEntity.from(createDeal)).toModel();
    }

    public Deal approveSale(Long userId, Long dealId) {
        User seller = getUserById(userId);
        Deal deal = getDealById(dealId);

        // 판매 승인 (feat.제품 상태: "거래 완료" 변경, 거래 상태: "판매 승인" 변경)
        Deal approveSaleDeal = deal.approveSale(seller);

        // save
        Product approveSaleProduct = approveSaleDeal.getProduct();
        productRepository.save(ProductEntity.from(approveSaleProduct));
        return dealRepository.save(DealEntity.from(approveSaleDeal)).toModel();
    }


    public ProductDetailForUserCommand findByIdForUser(Long userId, Long productId) {

        User user = getUserById(userId);
        Product product = getProductById(productId);

        // 구매 내역 조회 (요구사항 한 제품은 한번만 구매 가능)
        Deal purchaseDeal = dealRepository.findByBuyerIdAndProductId(user.getId(),
            product.getId()).map(DealEntity::toModel).orElse(null);


        // 판매 내역 조회
        List<Deal> saleDeals = dealRepository.findAllBySellerIdAndProductId(user.getId(),
                product.getId())
            .stream()
            .map(DealEntity::toModel)
            .toList();

        // 모든 거래 내역을 합친 리스트 반환 (경우1. 구매 내역만 존재, 경우2. 판매 내역만 존재, 경우3 아무것도 존재하지 않음)
        return new ProductDetailForUserCommand(product, purchaseDeal, saleDeals);
    }

    public List<Deal> findPurchasesHistoryByUserId(Long userId) {
        User user = getUserById(userId);
        return dealRepository.findAllByBuyerId(user.getId())
            .stream().map(DealEntity::toModel).toList();
    }

    public List<Deal> findSalesHistoryByUserId(Long userId) {
        User user = getUserById(userId);
        return dealRepository.findAllBySellerIdAndStatus(user.getId(), DealStatus.APPROVED)
            .stream().map(DealEntity::toModel).toList();
    }

    public List<Product> findSalesNow(Long userId) {
        User user = getUserById(userId);
        return productRepository.findAllBySellerIdAndStatusIn(user.getId(),
                List.of(ProductStatus.ON_SALE, ProductStatus.RESERVATION))
            .stream().map(ProductEntity::toModel).toList();
    }

    private User getUserById(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(NotAuthorizedDealToProductException::new)
            .toModel();
    }

    private Product getProductById(Long productId) {
        return productRepository.findById(productId)
            .orElseThrow(ProductNotFoundException::new)
            .toModel();
    }

    private Deal getDealById(Long dealId) {
        return dealRepository.findById(dealId)
            .orElseThrow(DealNotFoundException::new)
            .toModel();
    }
}
