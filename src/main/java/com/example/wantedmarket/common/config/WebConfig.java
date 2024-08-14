package com.example.wantedmarket.common.config;

import com.example.wantedmarket.common.interceptor.AuthorizationInterceptor;
import com.example.wantedmarket.common.resolver.TokenByUserIdResolver;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final AuthorizationInterceptor authorizationInterceptor;
    private final TokenByUserIdResolver tokenByUserIdResolver;

    private final List<String> PASS_URL = List.of(
        "/api/v1/register",
        "/api/v1/login",
        "/api/v1/products",
        "/api/v1/products/*"
    );

    private final List<String> DEFAULT_EXCLUDE = List.of(
        "/",
        "favicon.ico",
        "/error"
    );

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 인증 인터셉터 등록
        registry.addInterceptor(authorizationInterceptor)
            .excludePathPatterns(PASS_URL)
            .excludePathPatterns(DEFAULT_EXCLUDE);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(tokenByUserIdResolver);
    }
}
