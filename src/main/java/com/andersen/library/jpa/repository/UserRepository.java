package com.andersen.library.jpa.repository;

import com.andersen.library.jpa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u from User u join fetch u.roles where u.username = :name")
    User findByUsername(@Param("name") String name);
}
