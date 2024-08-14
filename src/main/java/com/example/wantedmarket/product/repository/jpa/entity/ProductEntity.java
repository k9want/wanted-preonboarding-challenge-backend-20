package com.example.wantedmarket.product.repository.jpa.entity;

import com.example.wantedmarket.user.repository.jpa.entity.UserEntity;
import com.example.wantedmarket.user.service.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.example.wantedmarket.product.service.domain.enums.ProductStatus;
import com.example.wantedmarket.product.service.domain.Product;

@EntityListeners(AuditingEntityListener.class)
@Table(name = "product")
@Entity
@Getter
@NoArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private UserEntity seller;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @Builder
    public ProductEntity(Long id, String name, Double price, UserEntity seller,
        ProductStatus status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.seller = seller;
        this.status = status;
    }

    public static ProductEntity from(Product product) {
        return ProductEntity.builder()
            .id(product.getId())
            .name(product.getName())
            .price(product.getPrice())
            .seller(UserEntity.builder()
                .id(product.getSeller().getId())
                .username(product.getSeller().getUsername())
                .nickname(product.getSeller().getNickname())
                .build())
            .status(product.getStatus())
            .build();
    }

    public Product toModel() {
        return Product.builder()
            .id(this.getId())
            .name(this.getName())
            .price(this.getPrice())
            .seller(User.builder()
                .id(this.getSeller().getId())
                .username(this.getSeller().getUsername())
                .nickname(this.getSeller().getNickname())
                .build())
            .status(this.getStatus())
            .build();
    }

}
