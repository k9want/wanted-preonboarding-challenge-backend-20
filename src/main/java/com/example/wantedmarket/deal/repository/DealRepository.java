package com.example.wantedmarket.deal.repository;

import com.example.wantedmarket.deal.repository.jpa.entity.DealEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealRepository extends JpaRepository<DealEntity, Long> {
}
