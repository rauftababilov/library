package com.andersen.library.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PredefinedRole {

    ADMIN(1),
    ROOT(2);

    private final long id;

}
