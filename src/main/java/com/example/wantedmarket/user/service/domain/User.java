package com.example.wantedmarket.user.service.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class User {

    private final Long id;
    private final String username;
    private final String password;
    private final String nickname;

    @Builder
    public User(Long id, String username, String password, String nickname) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }

    public static User create(String username, String password, String nickname) {
        return User.builder()
            .username(username)
            .password(password)
            .nickname(nickname)
            .build();
    }


}
