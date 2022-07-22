package com.docuservice.service.impl;

import com.docuservice.api.controller.dto.DocumentResponseDto;
import com.docuservice.api.controller.dto.PostNewDocumentDto;
import com.docuservice.api.controller.exception.EntityObjectNotFoundException;
import com.docuservice.api.controller.mapper.DocumentMapper;
import com.docuservice.persistance.domain.Document;
import com.docuservice.persistance.repository.DocumentRepository;
import com.docuservice.security.model.User;
import com.docuservice.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final DocumentMapper documentMapper;

    @Override
    public DocumentResponseDto newDocument(PostNewDocumentDto postNewDocumentDto, User user) {
        final Document document = documentMapper.document(postNewDocumentDto);

        user.addUploadedDocument(document);
        documentRepository.save(document);

        return documentMapper.documentResponseDto(document);
    }

    @Override
    public DocumentResponseDto findDocumentById(Long id) {
        Document document =
                documentRepository.findById(id)
                        .orElseThrow(() -> new EntityObjectNotFoundException("Cannot found document with id " + id));

        return documentMapper.documentResponseDto(document);
    }

    @Override
    public Page<DocumentResponseDto> getAllPaged(Document.DOCUMENT_STATUS status, Pageable pageable) {
        Page<Document> documents = status == null ? documentRepository.findAll(pageable) :
                documentRepository.findAllByStatus(status, pageable);
        return documents
                .map(documentMapper::documentResponseDto);
    }

}
