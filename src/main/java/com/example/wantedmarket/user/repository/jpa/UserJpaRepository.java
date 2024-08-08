package com.example.wantedmarket.user.repository.jpa;

import com.example.wantedmarket.user.repository.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
}
