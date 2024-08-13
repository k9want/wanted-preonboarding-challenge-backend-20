package com.example.wantedmarket.user.repository.jpa.entity;

import com.example.wantedmarket.user.service.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Table(name = "user")
@Entity
@Getter
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String username;
    private String password;
    private String nickname;

    @Builder
    public UserEntity(Long id, String username, String password, String nickname) {
        Id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }

    public static UserEntity from(User user) {
        return UserEntity.builder()
            .id(user.getId())
            .username(user.getUsername())
            .password(user.getPassword())
            .nickname(user.getNickname())
            .build();
    }

    public User toModel() {
        return User.builder()
            .id(this.getId())
            .username(this.getUsername())
            .password(this.getPassword())
            .nickname(this.getNickname())
            .build();
    }
}
