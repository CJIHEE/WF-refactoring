package com.workFlow.WFrefactoring.dto;

import com.workFlow.WFrefactoring.document.dto.AttachmentServiceDto;
import com.workFlow.WFrefactoring.document.dto.DocumentServiceDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class DocumentRequest {
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class WriteDocument{
        @NotNull
        private long requester;
        @NotNull
        private String subject;
        @NotNull
        private String contents;
        @NotNull
        private String expiredAt;
        @NotNull
        private boolean complete;

        //api -> domain DTO변환(documentDto,attachmentDto 분기)
        public DocumentServiceDto.WriteDocument convertToDocumentDto(){
            return DocumentServiceDto.WriteDocument.builder()
                    .requester(this.requester)
                    .subject(this.subject)
                    .contents(this.contents)
                    .expiredAt(this.expiredAt)
                    .build();
        }

        public AttachmentServiceDto.uploadAttachment convertToAttachmentDto(MultipartFile multipartFile){
            return AttachmentServiceDto.uploadAttachment.builder()
                    .multipartFile(multipartFile)
                    .build();
        }

    }
}
