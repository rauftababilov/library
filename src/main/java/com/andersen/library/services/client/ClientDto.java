package com.andersen.library.services.client;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.PastOrPresent;
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

}
