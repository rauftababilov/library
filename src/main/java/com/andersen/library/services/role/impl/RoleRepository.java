package com.andersen.library.services.role.impl;

import com.andersen.library.services.role.impl.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
