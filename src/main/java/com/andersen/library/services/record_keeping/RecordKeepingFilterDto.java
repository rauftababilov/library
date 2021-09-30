package com.andersen.library.services.record_keeping;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecordKeepingFilterDto {

    private Long clientId;

    private Long bookId;

    private BookState bookState;

}
