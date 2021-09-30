package com.andersen.library.services.publishing_house;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import java.util.LinkedList;
import java.util.List;

@Data
@Builder
public class PublishingHouseDto {

    @Null
    private Long id;

    @NotBlank
    private String title;
}
