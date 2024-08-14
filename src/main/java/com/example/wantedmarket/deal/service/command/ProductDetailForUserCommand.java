package com.example.wantedmarket.deal.service.command;

import com.example.wantedmarket.deal.service.domain.Deal;
import com.example.wantedmarket.product.service.domain.Product;
import java.util.List;

public record ProductDetailForUserCommand(
    Product product,
    Deal purchaseDeal,
    List<Deal> salesDeals
) {

}
