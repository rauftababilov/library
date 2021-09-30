package com.andersen.library.security;

import com.andersen.library.services.user.dto.UserDto;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@Data
public class UserDetailsImpl implements UserDetails {

    private static final String ROLE_PREFIX = "ROLE_";

    private final UserDto userDto;

    private Collection<SimpleGrantedAuthority> authorities;

    public UserDetailsImpl(UserDto userDto) {
        this.userDto = userDto;
        this.authorities = userDto.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(ROLE_PREFIX + role))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return userDto.getPassword();
    }

    @Override
    public String getUsername() {
        return userDto.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}