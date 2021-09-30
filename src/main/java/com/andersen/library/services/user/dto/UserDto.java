package com.andersen.library.services.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {

    @Null
    private Long id;

    @AssertFalse
    private boolean deleted;

    @NotBlank
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank
    private String password;

    private Set<String> roles = new HashSet<>();

}
