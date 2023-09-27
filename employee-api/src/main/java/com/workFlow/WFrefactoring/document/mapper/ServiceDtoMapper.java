package com.workFlow.WFrefactoring.document.mapper;

import com.workFlow.WFrefactoring.document.dto.AttachmentServiceDto;
import com.workFlow.WFrefactoring.document.dto.DocumentServiceDto;
import com.workFlow.WFrefactoring.document.dto.DocumentRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ServiceDtoMapper {

    // DocumentRequest.WriteDocument request -> DocumentServiceDto.WriteDocument 변환
    public static DocumentServiceDto.WriteDocument mapping(DocumentRequest.WriteDocument request){
        return DocumentServiceDto.WriteDocument.builder()
                .requester(request.getRequester())
                .subject(request.getSubject())
                .contents(request.getContents())
                .expiredAt(request.getExpiredAt())
                .build();
    }

    // List<MultipartFile> multipartFileList -> AttachmentServiceDto.UploadAttachment
    public static AttachmentServiceDto.UploadAttachment mapping(List<MultipartFile> multipartFileList){
        return AttachmentServiceDto.UploadAttachment.builder()
                .multipartFileList(multipartFileList)
                .build();
    }

}
