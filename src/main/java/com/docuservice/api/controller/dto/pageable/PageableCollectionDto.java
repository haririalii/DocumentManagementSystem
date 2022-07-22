package com.docuservice.api.controller.dto.pageable;

import lombok.Data;

import java.util.Collection;

@Data
public class PageableCollectionDto<D> {

    private Collection<D> items;
    private long totalCount;

}
