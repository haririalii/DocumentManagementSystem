package com.docuservice.persistance.repository;


import com.docuservice.persistance.file.MinioBucket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MinioBucketRepository extends JpaRepository<MinioBucket, Long> {
}
