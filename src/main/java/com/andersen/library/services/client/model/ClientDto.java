package com.andersen.library.services.client.model;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class ClientDto {

    @Null
    private Long id;

    @NotBlank
    private String fullName;

    @PastOrPresent
    @NotNull
    private LocalDate birthday;

    @AssertFalse
    private boolean deleted;

}
