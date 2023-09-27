package com.workFlow.WFrefactoring.document.dto;

import com.workFlow.WFrefactoring.model.Attachment;
import com.workFlow.WFrefactoring.model.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AttachmentServiceDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UploadAttachment {
        private Long docNo;
        private List<String> fileNameList = new ArrayList<>();
        private List<String> orgFileNameList = new ArrayList<>();
        private List<Long> fileSizeList = new ArrayList<>();
        private List<MultipartFile> multipartFileList = new ArrayList<>();

        //DTO -> Entity
        public Attachment toAttachment(Document document, int idx){
            return Attachment.builder()
                    .orgFileName(this.orgFileNameList.get(idx))
                    .fileName(this.orgFileNameList.get(idx))
                    .fileSize(this.fileSizeList.get(idx))
                    .document(document)
                    .build();
        }

        // Factory Method

        // 정적 팩토리 메서드 패턴


    }
}
