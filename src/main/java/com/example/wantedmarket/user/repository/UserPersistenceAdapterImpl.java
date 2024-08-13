package com.example.wantedmarket.user.repository;

import com.example.wantedmarket.user.repository.jpa.UserJpaRepository;
import com.example.wantedmarket.user.repository.jpa.entity.UserEntity;
import com.example.wantedmarket.user.service.persistence.UserPersistenceAdapter;
import com.example.wantedmarket.user.service.domain.User;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserPersistenceAdapterImpl implements UserPersistenceAdapter {

    private final UserJpaRepository userJpaRepository;

    @Override
    public User findById(Long userId) {
        Optional<UserEntity> optionalUserEntity = userJpaRepository.findById(userId);
        return optionalUserEntity.map(this::mapToDomain).orElse(null);

    }

    @Override
    public User findByUsername(String username) {
        Optional<UserEntity> optionalUserEntity = userJpaRepository.findByUsername(username);
        return optionalUserEntity.map(this::mapToDomain).orElse(null);

    }

    @Override
    public User save(User user) {
        UserEntity entity = null;

        if (user.getId() != null) {
            entity = userJpaRepository.findById(user.getId())
                .orElse(null);
        }

        if (entity == null) {
            entity = mapToEntity(user);
        }

        return mapToDomain(userJpaRepository.save(entity));
    }


    private UserEntity mapToEntity(User user) {
        return UserEntity.builder()
            .id(user.getId())
            .username(user.getUsername())
            .password(user.getPassword())
            .nickname(user.getNickname())
            .build();
    }


    private User mapToDomain(UserEntity userEntity) {
        return User.builder()
            .id(userEntity.getId())
            .username(userEntity.getUsername())
            .password(userEntity.getPassword())
            .nickname(userEntity.getNickname())
            .build();
    }
}
