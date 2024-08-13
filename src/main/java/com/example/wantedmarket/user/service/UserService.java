package com.example.wantedmarket.user.service;

import com.example.wantedmarket.user.repository.jpa.UserRepository;
import com.example.wantedmarket.user.repository.jpa.entity.UserEntity;
import com.example.wantedmarket.user.service.domain.User;
import com.example.wantedmarket.user.service.exception.AuthInvalidPasswordException;
import com.example.wantedmarket.user.service.exception.AuthUserNameNotFoundException;
import com.example.wantedmarket.user.service.exception.DuplicateUserNameException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User register(String username, String password, String nickname) {

        userRepository.findByUsername(username).ifPresent(it -> {
            throw new DuplicateUserNameException();
        });

        final User user = User.create(username, password, nickname);
        return userRepository.save(UserEntity.from(user)).toModel();
    }

    public User login(String username, String password) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(AuthUserNameNotFoundException::new)
            .toModel();

        if (!user.getPassword().equals(password)) {
            throw new AuthInvalidPasswordException();
        }

        return user;
    }
}
