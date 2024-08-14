package com.example.wantedmarket.common.token.service;

import com.example.wantedmarket.common.token.Token;
import com.example.wantedmarket.common.token.ifs.TokenHelperIfs;
import com.example.wantedmarket.common.token.service.exception.TokenNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtTokenService {

    private final TokenHelperIfs tokenHelperIfs;

    public Token issueToken(Long tokenByUserId) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("tokenByUserId", tokenByUserId);
        return tokenHelperIfs.issueAccessToken(data);
    }

    public Long validationAccessToken(String token) {
        Map<String, Object> map = tokenHelperIfs.validationTokenWithThrow(token);
        Object userId = map.get("tokenByUserId");

        Objects.requireNonNull(userId, () -> {
            throw new TokenNotFoundException();
        });

        return Long.parseLong(userId.toString());
    }
}
