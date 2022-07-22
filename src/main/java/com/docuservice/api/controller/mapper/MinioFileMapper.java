package com.docuservice.api.controller.mapper;

import com.docuservice.api.controller.dto.file.FileDto;
import com.docuservice.persistance.file.MinioFile;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, componentModel = "spring")
public interface MinioFileMapper {

    FileDto fileDto(MinioFile minioFile);
    MinioFile minioFile(FileDto fileDto);

}
