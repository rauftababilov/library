package com.andersen.library.service;

import com.andersen.library.jpa.domain.User;

public interface UserService {
    User findByUsername(String name);
}
