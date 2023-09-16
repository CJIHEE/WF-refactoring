package com.workFlow.WFrefactoring.utils;

import com.workFlow.WFrefactoring.document.dto.AttachmentServiceDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
@Slf4j
public class FileManager {
    @Value("${file.upload-dir}")
    String fileDir;

    //return AttachedDTO로 바꿀거임
    public AttachmentServiceDto.uploadAttachment uploadFile(MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();

        String storeFilename = CreateFileName(originalFilename);

        multipartFile.transferTo(new File(CreatePath(storeFilename)));
        return AttachmentServiceDto.uploadAttachment.builder()
                .orgFileName(originalFilename)
                .fileName(storeFilename)
                .fileSize(multipartFile.getSize())
                .build();

    }

    private String CreateFileName(String originalFilename){
        UUID uuid = UUID.randomUUID();
        return uuid + "_" + originalFilename;
    }
    private String CreatePath(String fileName){
        return fileDir + fileName;
    }
}
