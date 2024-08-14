package com.example.wantedmarket.deal.service;

import com.example.wantedmarket.deal.repository.DealRepository;
import com.example.wantedmarket.deal.repository.jpa.entity.DealEntity;
import com.example.wantedmarket.deal.service.domain.Deal;
import com.example.wantedmarket.deal.service.exception.NotAuthorizedProductToBuyException;
import com.example.wantedmarket.deal.service.exception.ToPurchaseProductNotFoundException;
import com.example.wantedmarket.product.repository.jpa.entity.ProductEntity;
import com.example.wantedmarket.product.repository.jpa.entity.ProductRepository;
import com.example.wantedmarket.product.service.domain.Product;
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

        User user = getByUserId(userId);

        Product product = productRepository.findById(productId)
            .orElseThrow(ToPurchaseProductNotFoundException::new)
            .toModel();

        Product reservedProduct = product.reserve(user);
        Deal deal = Deal.create(user, product);

        // 상태가 변경된 product 저장
        productRepository.save(ProductEntity.from(reservedProduct));
        return dealRepository.save(DealEntity.from(deal)).toModel();
    }

    public List<Deal> findPurchasesHistoryByUserId(Long userId) {
        User user = getByUserId(userId);
        return dealRepository.findByBuyerId(userId)
            .stream().map(DealEntity::toModel).toList();
    }

    private User getByUserId(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(NotAuthorizedProductToBuyException::new)
            .toModel();
    }
}
