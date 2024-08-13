package com.example.wantedmarket.user.service.persistence;

import com.example.wantedmarket.user.service.domain.User;


public interface UserPersistenceAdapter {

    User findById(Long userId);

    User findByUsername(String username);

    User save(User user);

}
