package com.docuservice.api.controller.mapper;

import com.docuservice.api.controller.dto.DocumentResponseDto;
import com.docuservice.api.controller.dto.PostNewDocumentDto;
import com.docuservice.api.controller.dto.UserResponseDto;
import com.docuservice.api.controller.dto.file.FileDto;
import com.docuservice.persistance.domain.Document;
import com.docuservice.persistance.file.MinioFile;
import com.docuservice.security.model.User;
import com.github.sbahmani.jalcal.util.JalCal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.Date;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL, componentModel = "spring")
public interface DocumentMapper {

    @Named("gregorianToJalali")
    public static String gregorianToJalali(Date date) {
        return JalCal.gregorianToJalaliDate(date, false);
    }

    @Named("statusToPersian")
    public static String statusToPersian(Document.DOCUMENT_STATUS status) {
        //TODO use i18n
        if (status == null)
            return "نا مشخص";

        switch (status) {
            case PENDING:
                return "در انتظار تایید";
            case APPROVED:
                return "تایید شده";
            case REJECTED:
                return "رد شده";
            default:
                return null;
        }
    }

    PostNewDocumentDto postNewDocumentDto(Document document);
    Document document(PostNewDocumentDto postNewDocumentDto);

    FileDto fileDto(MinioFile minioFile);
    MinioFile minioFile(FileDto fileDto);

    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "gregorianToJalali")
    @Mapping(source = "updatedAt", target = "updatedAt", qualifiedByName = "gregorianToJalali")
    UserResponseDto userResponseDto(User user);

    @Mapping(source = "documentCreated", target = "documentCreated", qualifiedByName = "gregorianToJalali")
    @Mapping(source = "status", target = "status", qualifiedByName = "statusToPersian")
    DocumentResponseDto documentResponseDto(Document document);

}
