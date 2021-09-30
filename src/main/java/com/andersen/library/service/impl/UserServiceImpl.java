package com.andersen.library.service.impl;

import com.andersen.library.jpa.domain.User;
import com.andersen.library.jpa.repository.UserRepository;
import com.andersen.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User findByUsername(String name) {
        return userRepository.findByUsername(name);
    }
}
