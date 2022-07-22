package com.docuservice.service;

import com.docuservice.api.controller.dto.DocumentResponseDto;
import com.docuservice.api.controller.dto.PostNewDocumentDto;
import com.docuservice.persistance.domain.Document;
import com.docuservice.security.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DocumentService {

    DocumentResponseDto newDocument(PostNewDocumentDto postNewDocumentDto, User user);
    DocumentResponseDto findDocumentById(Long id);
    Page<DocumentResponseDto> getAllPaged(Document.DOCUMENT_STATUS status, Pageable pageable);

}
