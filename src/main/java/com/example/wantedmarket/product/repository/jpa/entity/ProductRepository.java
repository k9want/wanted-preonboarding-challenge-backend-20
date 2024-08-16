package com.example.wantedmarket.product.repository.jpa.entity;

import com.example.wantedmarket.product.service.domain.enums.ProductStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findAllBySellerIdAndStatusIn(Long sellerId, List<ProductStatus> status);
}
