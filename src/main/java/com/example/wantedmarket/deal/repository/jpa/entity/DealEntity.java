package com.example.wantedmarket.deal.repository.jpa.entity;

import com.example.wantedmarket.deal.service.domain.Deal;
import com.example.wantedmarket.deal.service.domain.enums.DealStatus;
import com.example.wantedmarket.product.repository.jpa.entity.ProductEntity;
import com.example.wantedmarket.user.repository.jpa.entity.UserEntity;
import com.example.wantedmarket.user.service.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "deal")
@Getter
@NoArgsConstructor
public class DealEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id")
    private UserEntity buyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private UserEntity seller;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Enumerated(EnumType.STRING)
    private DealStatus status;

    @Builder
    public DealEntity(Long id, UserEntity buyer, UserEntity seller, ProductEntity product,
        DealStatus status) {
        this.id = id;
        this.buyer = buyer;
        this.seller = seller;
        this.product = product;
        this.status = status;
    }

    public static DealEntity from(Deal deal) {
        return DealEntity.builder()
            .id(deal.getId())
            .buyer(UserEntity.builder()
                .id(deal.getBuyer().getId())
                .username(deal.getBuyer().getUsername())
                .nickname(deal.getBuyer().getNickname())
                .build())
            .seller(UserEntity.builder()
                .id(deal.getSeller().getId())
                .username(deal.getSeller().getUsername())
                .nickname(deal.getSeller().getNickname())
                .build())
            .product(ProductEntity.from(deal.getProduct()))
            .status(deal.getStatus())
            .build();
    }

    public Deal toModel() {
        return Deal.builder()
            .id(this.getId())
            .buyer(User.builder()
                .id(this.getBuyer().getId())
                .username(this.getBuyer().getUsername())
                .nickname(this.getBuyer().getNickname())
                .build())
            .seller(User.builder()
                .id(this.getSeller().getId())
                .username(this.getSeller().getUsername())
                .nickname(this.getSeller().getNickname())
                .build())
            .product(this.getProduct().toModel())
            .status(this.getStatus())
            .build();
    }
}
