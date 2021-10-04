package com.andersen.library.jpa;

import com.andersen.library.security.SpringSecurityUtils;
import com.andersen.library.services.user.model.UserDto;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        return SpringSecurityUtils.getCurrentUser().map(UserDto::getId);
    }

}