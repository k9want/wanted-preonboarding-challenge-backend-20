package com.example.wantedmarket.product.service;

import com.example.wantedmarket.deal.repository.DealRepository;
import com.example.wantedmarket.product.repository.jpa.entity.ProductEntity;
import com.example.wantedmarket.product.repository.jpa.entity.ProductRepository;
import com.example.wantedmarket.product.service.domain.Product;
import com.example.wantedmarket.product.service.exception.ProductNotFoundException;
import com.example.wantedmarket.product.service.exception.UserNotFoundException;
import com.example.wantedmarket.user.repository.jpa.UserRepository;
import com.example.wantedmarket.user.service.domain.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final DealRepository dealRepository;

    @Transactional
    public Product register(Long userId, String name, Double price) {

        // 비회원이 제품 등록 시도 시
        User seller = getUserById(userId);
        Product product = Product.register(name, price, seller);
        return productRepository.save(ProductEntity.from(product)).toModel();
    }

    public Product findByIdForGuest(Long productId) {
        return productRepository.findById(productId)
            .orElseThrow(ProductNotFoundException::new)
            .toModel();
    }

    private User getUserById(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(UserNotFoundException::new)
            .toModel();
    }

    // todo: 페이지네이션, N+1
    public List<Product> findAll() {
        return productRepository.findAll().stream().map(ProductEntity::toModel).toList();
    }
}
