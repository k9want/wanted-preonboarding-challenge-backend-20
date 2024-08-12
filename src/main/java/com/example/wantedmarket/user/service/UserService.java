package com.example.wantedmarket.user.service;

import com.example.wantedmarket.user.service.domain.User;
import com.example.wantedmarket.user.service.exception.LoginUserNotFoundException;
import com.example.wantedmarket.user.service.exception.UserNotFoundException;
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

    public User login(String username, String password) {
        User user = userPersistenceAdapter.findByUsernameAndPassword(username,
            password);

        if (user == null) {
            throw new LoginUserNotFoundException();
        }

        return user;
    }
}
