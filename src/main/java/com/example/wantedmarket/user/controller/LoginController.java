package com.example.wantedmarket.user.controller;

import static com.example.wantedmarket.user.controller.consts.UserErrorCode.LOGIN_USER_NOT_FOUND;

import com.example.wantedmarket.common.controller.ApiResponse;
import com.example.wantedmarket.common.exception.WantedMarketHttpException;
import com.example.wantedmarket.user.controller.dto.UserLoginRequest;
import com.example.wantedmarket.user.controller.dto.UserLoginResponse;
import com.example.wantedmarket.user.service.UserLoginFacade;
import com.example.wantedmarket.user.service.command.LoginCommand;
import com.example.wantedmarket.user.service.exception.LoginUserNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class LoginController {

    private final UserLoginFacade userLoginFacade;

    @PostMapping("/login")
    public ApiResponse<UserLoginResponse> login(
        @Valid @RequestBody UserLoginRequest request,
        HttpServletResponse response) {
        final LoginCommand result;

        try {
            result = userLoginFacade.login(request.username(), request.password());
        } catch (LoginUserNotFoundException e) {
            throw new WantedMarketHttpException(LOGIN_USER_NOT_FOUND, HttpStatus.NOT_FOUND);
        }

        response.setHeader("authorization-token", result.token().getToken());
        response.setHeader("authorization-token-expired-at", result.token().getExpiredAt().toString());

        UserLoginResponse body = UserLoginResponse.from(result.user());
        return ApiResponse.fromData(body);
    }

}
