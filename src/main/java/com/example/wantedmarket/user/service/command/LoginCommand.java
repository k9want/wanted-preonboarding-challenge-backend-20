package com.example.wantedmarket.user.service.command;

import com.example.wantedmarket.common.token.Token;
import com.example.wantedmarket.user.service.domain.User;

public record LoginCommand(
    User user,
    Token token
) {

}
