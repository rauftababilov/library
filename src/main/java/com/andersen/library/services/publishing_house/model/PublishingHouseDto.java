package com.andersen.library.services.publishing_house.model;

import lombok.Data;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

@Data
public class PublishingHouseDto {

    @Null
    private Long id;

    @NotBlank
    private String title;

    @AssertFalse
    private boolean deleted;

}
