package com.docuservice.persistance.file;

import com.docuservice.security.model.audit.DateAudit;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "minio_bucket")
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
public class MinioBucket extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, nullable = false, updatable = false)
    private String name;

}
