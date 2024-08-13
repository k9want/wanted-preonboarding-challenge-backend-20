package com.example.wantedmarket.user.service;

import com.example.wantedmarket.user.service.domain.User;
import com.example.wantedmarket.user.service.exception.AuthInvalidPasswordException;
import com.example.wantedmarket.user.service.exception.AuthUserNameNotFoundException;
import com.example.wantedmarket.user.service.exception.DuplicateUserNameException;
import com.example.wantedmarket.user.service.persistence.UserPersistenceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserPersistenceAdapter userPersistenceAdapter;

    public User register(String username, String password, String nickname) {

        User UserByUsername = userPersistenceAdapter.findByUsername(username);
        if (UserByUsername != null) {
            throw new DuplicateUserNameException();
        }

        final User user = User.create(username, password, nickname);
        return userPersistenceAdapter.save(user);
    }

    public User login(String username, String password) {
        User user = userPersistenceAdapter.findByUsername(username);

        if (user == null) {
            throw new AuthUserNameNotFoundException();
        }

        if (!user.getPassword().equals(password)) {
            throw new AuthInvalidPasswordException();
        }

        return user;
    }
}
