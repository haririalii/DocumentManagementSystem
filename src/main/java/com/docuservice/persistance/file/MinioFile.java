package com.docuservice.persistance.file;

import com.docuservice.security.model.audit.DateAudit;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "minio_files")
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
public class MinioFile extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "object_name", unique = true, nullable = false, updatable = false)
    private String objectName;

    @Column(name = "size")
    private Long size;

    @Column(name = "content_type")
    private String contentType;

    @ManyToOne
    @JoinColumn(name = "bucket_id")
    private MinioBucket bucket;

}
