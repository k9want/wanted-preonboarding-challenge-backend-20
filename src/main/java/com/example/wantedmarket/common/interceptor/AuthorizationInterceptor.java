package com.example.wantedmarket.common.interceptor;

import com.example.wantedmarket.common.controller.consts.CommonErrorCodes;
import com.example.wantedmarket.common.exception.WantedMarketHttpException;
import com.example.wantedmarket.common.token.service.JwtTokenService;
import com.example.wantedmarket.common.token.service.exception.ExpiredJwtTokenException;
import com.example.wantedmarket.common.token.service.exception.InvalidJwtTokenException;
import com.example.wantedmarket.common.token.service.exception.JwtTokenException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthorizationInterceptor implements HandlerInterceptor {

    private final JwtTokenService jwtTokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {
        log.info("Authorization Interceptor url : {}", request.getRequestURI());

        // WEB, Chrome 의 경우 GET, POST 전에 OPTIONS을 통해 해당 메서드를 지원하는지 체크하는데 = PASS
        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            return true;
        }

        // js, html, png, resource 요청 -PASS
        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }

        // 헤더에서 authorization-token 꺼내고
        String authorizationToken = request.getHeader("authorization-token");
        // 토큰이 없다면?
        if (authorizationToken == null) {
            throw new WantedMarketHttpException(
                CommonErrorCodes.UNAUTHORIZED_TOKEN_AUTHORIZATION_TOKEN_NOT_FOUND,
                HttpStatus.UNAUTHORIZED);
        }

        // 토큰이 유효한지 체크 후 userId 반환
        final Long tokenByUserId;
        try {
            tokenByUserId = jwtTokenService.validationAccessToken(authorizationToken);
        } catch (ExpiredJwtTokenException e) {
            throw new WantedMarketHttpException(CommonErrorCodes.UNAUTHORIZED_TOKEN_EXPIRED_TOKEN,
                HttpStatus.UNAUTHORIZED);
        } catch (InvalidJwtTokenException e) {
            throw new WantedMarketHttpException(CommonErrorCodes.BAD_REQUEST_TOKEN_INVALID_TOKEN,
                HttpStatus.BAD_REQUEST);
        } catch (JwtTokenException exception) {
            log.error("JWT Token Error", exception);
            throw new WantedMarketHttpException(
                CommonErrorCodes.INTERNAL_SERVER_ERROR_TOKEN_AUTHORIZATION_FAIL,
                HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // 현재 요청 request Context 에다가 userId를 저장한다.
        // 범위는 이번 요청 동안만! SCOPE_REQUEST
        RequestAttributes requestContext = Objects.requireNonNull(
            RequestContextHolder.getRequestAttributes());
        requestContext.setAttribute("tokenByUserId", tokenByUserId,
            RequestAttributes.SCOPE_REQUEST);
        return true;
    }
}
