package com.andersen.library.services.user.impl;

import com.andersen.library.services.user.impl.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
