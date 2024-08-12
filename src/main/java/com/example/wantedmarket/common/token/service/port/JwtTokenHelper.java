package com.example.wantedmarket.common.token.service.port;

import com.example.wantedmarket.common.exception.WantedMarketHttpException;
import com.example.wantedmarket.common.token.Token;
import com.example.wantedmarket.common.token.ifs.TokenHelperIfs;
import com.example.wantedmarket.common.token.service.exception.ExpiredJwtTokenException;
import com.example.wantedmarket.common.token.service.exception.InvalidJwtTokenException;
import com.example.wantedmarket.common.token.service.exception.JwtTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenHelper implements TokenHelperIfs {

    private final String secretKey;
    private final Long accessTokenPlusHour;

    public JwtTokenHelper(
        @Value("${jwt.secret-key}") String secretKey,
        @Value("${jwt.access-token-plus-hour}") Long accessTokenPlusHour) {
        this.secretKey = secretKey;
        this.accessTokenPlusHour = accessTokenPlusHour;
    }

    @Override
    public Token issueAccessToken(Map<String, Object> data) {
        LocalDateTime expiredLocalDateTime = LocalDateTime.now().plusHours(accessTokenPlusHour);
        java.util.Date expiredAt = Date.from(
            expiredLocalDateTime.atZone(ZoneId.systemDefault()).toInstant());

        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());

        String jwtToken = Jwts.builder()
            .signWith(key, SignatureAlgorithm.HS256)
            .setClaims(data)
            .setExpiration(expiredAt)
            .compact();

        return Token.builder()
            .token(jwtToken)
            .expiredAt(expiredLocalDateTime)
            .build();
    }

    @Override
    public Map<String, Object> validationTokenWithThrow(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());

        JwtParser parser = Jwts.parserBuilder()
            .setSigningKey(key)
            .build();

        try {
            Jws<Claims> result = parser.parseClaimsJws(token);
            return new HashMap<String, Object>(result.getBody());

        } catch (Exception e) {
            if (e instanceof SignatureException) {
                // 토큰이 유효하지 않을 때
                throw new InvalidJwtTokenException();
            }
            else if (e instanceof ExpiredJwtException) {
                // 만료된 토큰
                throw new ExpiredJwtTokenException();
            }
            else {
                // 그외 에러
                throw new JwtTokenException(e);
            }
        }
    }
}
