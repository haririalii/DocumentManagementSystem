package com.docuservice.api.controller.dto.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDto {

    private String id;
    private String objectName;
    private Long size;
    private String contentType;

}
