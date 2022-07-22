package com.docuservice.api.controller.dto;

import com.docuservice.api.controller.dto.file.FileDto;
import com.docuservice.api.controller.dto.user.UserResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Getter
@Setter
public class DocumentResponseDto extends RepresentationModel<DocumentResponseDto> {

    private Long id;

    @JsonProperty("number")
    private String documentNumber;

    @JsonProperty("creator")
    private UserResponseDto uploadedBy;

    @JsonProperty("receivers")
    private List<UserResponseDto> documentReceivers;

//    private List<UserGroup> accessedByGroup;

    private String title;

    private String text;

    @JsonProperty("creation_date")
    private String documentCreated;

    @JsonProperty("last_modification_date")
    private String documentUpdated;

    private String status;

    private FileDto file;

}
