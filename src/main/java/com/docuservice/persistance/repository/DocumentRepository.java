package com.docuservice.persistance.repository;

import com.docuservice.persistance.domain.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    Document getDocumentByDocumentNumber(String documentNumber);

    Page<Document> findAllByStatus(Document.DOCUMENT_STATUS status, Pageable pageable);

}
