package com.workFlow.WFrefactoring.document.dto;

import com.workFlow.WFrefactoring.model.Attachment;
import com.workFlow.WFrefactoring.model.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class AttachmentServiceDto {

    @Getter
    @NoArgsConstructor
    public static class UploadAttachment {
        private String fileName;
        private String orgFileName;
        private Long fileSize;
        private List<MultipartFile> multipartFileList = new ArrayList<>();

        @Builder
        public UploadAttachment(String fileName, String orgFileName, Long fileSize, List<MultipartFile> multipartFileList) {
            this.fileName = fileName;
            this.orgFileName = orgFileName;
            this.fileSize = fileSize;
            this.multipartFileList = multipartFileList;
        }
    }
}
