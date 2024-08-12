package com.example.wantedmarket.user.controller;

import com.example.wantedmarket.common.controller.ApiResponse;
import com.example.wantedmarket.user.controller.dto.UserRegisterRequest;
import com.example.wantedmarket.user.controller.dto.UserRegisterResponse;
import com.example.wantedmarket.user.service.UserService;
import com.example.wantedmarket.user.service.domain.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<UserRegisterResponse> register(
        @Valid @RequestBody UserRegisterRequest request) {
        final User user = userService.register(request.username(), request.password(),
            request.nickname());
        UserRegisterResponse response = UserRegisterResponse.from(user);
        return ApiResponse.fromData(response);
    }
}
