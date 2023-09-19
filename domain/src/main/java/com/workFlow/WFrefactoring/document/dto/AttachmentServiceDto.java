package com.workFlow.WFrefactoring.document.dto;

import com.workFlow.WFrefactoring.model.Attachment;
import com.workFlow.WFrefactoring.model.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class AttachmentServiceDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class uploadAttachment{
        @NotNull
        private Long docNo;
        @NotNull
        private List<String> fileNameList = new ArrayList<>();
        @NotNull
        private List<String> orgFileNameList = new ArrayList<>();
        @NotNull
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


    }
}
