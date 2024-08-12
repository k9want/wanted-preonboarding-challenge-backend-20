package com.example.wantedmarket.common.token;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class Token {

    private final String token;
    private final LocalDateTime expiredAt;

    @Builder
    public Token(String token, LocalDateTime expiredAt) {
        this.token = token;
        this.expiredAt = expiredAt;
    }
}
