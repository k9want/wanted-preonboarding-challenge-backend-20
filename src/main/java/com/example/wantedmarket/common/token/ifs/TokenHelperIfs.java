package com.example.wantedmarket.common.token.ifs;

import com.example.wantedmarket.common.token.Token;
import java.util.Map;

public interface TokenHelperIfs {
    Token issueAccessToken(Map<String, Object> data);

    Map<String, Object> validationTokenWithThrow(String token);

}
