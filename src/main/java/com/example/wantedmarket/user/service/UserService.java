package com.example.wantedmarket.user.service;

import com.example.wantedmarket.user.service.domain.User;
import com.example.wantedmarket.user.service.persistence.UserPersistenceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserPersistenceAdapter userPersistenceAdapter;

    public User register(String username, String password, String nickname) {

        final User user = User.create(username, password, nickname);
        return userPersistenceAdapter.save(user);
    }
}
