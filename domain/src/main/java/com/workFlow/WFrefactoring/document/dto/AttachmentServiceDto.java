package com.workFlow.WFrefactoring.document.dto;

import com.workFlow.WFrefactoring.model.Attachment;
import com.workFlow.WFrefactoring.model.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

public class AttachmentServiceDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class uploadAttachment{
        @NotNull
        private Long docNo;
        @NotNull
        private String fileName;
        @NotNull
        private String orgFileName;
        @NotNull
        private Long fileSize;
        private MultipartFile multipartFile;

        //DTO -> Entity
        public Attachment toAttachment(Document document){
            return Attachment.builder()
                    .orgFileName(this.orgFileName)
                    .fileName(this.fileName)
                    .fileSize(this.fileSize)
                    .document(document)
                    .build();
        }


    }
}
