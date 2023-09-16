package com.workFlow.WFrefactoring.document;

import com.workFlow.WFrefactoring.document.dto.AttachmentServiceDto;
import com.workFlow.WFrefactoring.document.dto.DocumentResponse;
import com.workFlow.WFrefactoring.document.dto.DocumentServiceDto;
import com.workFlow.WFrefactoring.document.service.DocumentService;
import com.workFlow.WFrefactoring.dto.DocumentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("documents")
@RequiredArgsConstructor
@Slf4j
public class DocumentController {
    private final DocumentService documentService;
    //문서 작성
    @PostMapping
    public DocumentResponse writeDocument(@RequestPart(value="request") @Valid DocumentRequest.WriteDocument request,
                                          @RequestPart(value="file", required = false) MultipartFile multipartFile) throws IOException {
//        if(multipartFile!=null){
//            log.info("dto변환");
//            request.convertToAttachmentDto(multipartFile);
//            log.info("multipartFile : " + new AttachmentServiceDto.uploadAttachment.)
//        }
        return documentService.wrtieDocument(request.convertToDocumentDto(),request.convertToAttachmentDto(multipartFile));
    }

}
