package com.andersen.library.security;


import com.andersen.library.exceptions.ExceptionType;
import com.andersen.library.exceptions.ExtException;
import com.andersen.library.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return new UserDetailsImpl(userService.getByUsername(username, false));
        } catch (ExtException e) {
            if (e.getType().equals(ExceptionType.USER_NOT_FOUND)) {
                throw new UsernameNotFoundException(e.getMessage());
            } else {
                throw e;
            }
        }
    }

}
