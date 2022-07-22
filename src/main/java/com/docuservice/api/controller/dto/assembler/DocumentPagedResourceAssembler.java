package com.docuservice.api.controller.dto.assembler;

import com.docuservice.api.controller.dto.DocumentResponseDto;
import com.docuservice.api.controller.mapper.DocumentMapper;
import com.docuservice.persistance.domain.Document;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DocumentPagedResourceAssembler implements RepresentationModelAssembler<Document, DocumentResponseDto> {

    private final DocumentMapper documentMapper;

    @Override
    public DocumentResponseDto toModel(Document entity) {
        return documentMapper.documentResponseDto(entity);
    }

}
