package com.docuservice.api.controller;


import com.docuservice.api.controller.dto.DocumentResponseDto;
import com.docuservice.api.controller.dto.PostNewDocumentDto;
import com.docuservice.persistance.domain.Document;
import com.docuservice.persistance.repository.DocumentRepository;
import com.docuservice.security.UserPrincipal;
import com.docuservice.security.model.User;
import com.docuservice.service.DocumentService;
import com.docuservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/documents")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class DocumentController {

    private final DocumentRepository documentRepository;
    private final DocumentService documentService;
    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<PagedModel<EntityModel<DocumentResponseDto>>> getDocuments(@RequestParam(required = false) Document.DOCUMENT_STATUS status, @PageableDefault Pageable pageable,
                                                                                        PagedResourcesAssembler<DocumentResponseDto> pagedAssembler) {
        return ResponseEntity.ok(
                pagedAssembler.toModel(
                        documentService.getAllPaged(status, pageable)
                )
        );
    }

    @GetMapping("/{documentNumber}")
    public Document getDocuments(@PathParam("{documentNumber}") String documentNumber) {


        return this.documentRepository.getDocumentByDocumentNumber(documentNumber); //
    }

    @PostMapping
    public ResponseEntity<DocumentResponseDto> addNewDocument(@RequestBody PostNewDocumentDto newDocumentRequest,
                                                              @AuthenticationPrincipal UserPrincipal userPrincipal) {

        return ResponseEntity.ok(
                documentService.newDocument(newDocumentRequest,
                        this.getCurrentUser(userPrincipal))
        );
    }

    private User getCurrentUser(UserPrincipal userPrincipal) {
        return userService.findUserByUserPrincipal(userPrincipal);
    }
}
