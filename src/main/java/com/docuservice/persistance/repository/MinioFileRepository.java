package com.docuservice.persistance.repository;


import com.docuservice.persistance.file.MinioFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MinioFileRepository extends JpaRepository<MinioFile, Long> {
}
