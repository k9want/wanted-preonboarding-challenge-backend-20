package com.example.wantedmarket.user.service;

import com.example.wantedmarket.common.token.Token;
import com.example.wantedmarket.common.token.service.JwtTokenService;
import com.example.wantedmarket.user.service.domain.User;
import com.example.wantedmarket.user.service.command.LoginCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLoginFacade {

    private final UserService userService;
    private final JwtTokenService jwtTokenService;

    public LoginCommand login(String username, String password) {
        final User user = userService.login(username, password);
        Token token = jwtTokenService.issueToken(user.getId());

        return new LoginCommand(user, token);
    }
}
