package com.andersen.library.services.user.dto;

import lombok.Data;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto {

    @Null
    private Long id;

    @AssertFalse
    private boolean deleted;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private List<RoleDto> roles = new ArrayList<>();

}
