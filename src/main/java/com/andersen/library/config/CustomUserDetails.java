package com.andersen.library.config;

import com.andersen.library.jpa.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;


public class CustomUserDetails implements UserDetails {
    private String login;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public static CustomUserDetails fromUserEntityToCustomUserDetails(User user) {
        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.login = user.getUsername();
        customUserDetails.password = user.getPassword();
        customUserDetails.authorities = user.getRoles().stream()
                .map(x -> new SimpleGrantedAuthority(x.getName()))
                .collect(Collectors.toList());

        return customUserDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
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
