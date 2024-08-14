package com.example.wantedmarket.deal.repository;

import com.example.wantedmarket.deal.repository.jpa.entity.DealEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealRepository extends JpaRepository<DealEntity, Long> {

    List<DealEntity> findAllByBuyerId(Long buyerId);
    List<DealEntity> findAllBySellerId(Long buyerId);
    Optional<DealEntity> findByBuyerIdAndProductId(Long buyerId, Long productId);
    List<DealEntity> findAllBySellerIdAndProductId(Long seller, Long productId);
}
