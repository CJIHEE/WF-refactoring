package com.workFlow.WFrefactoring.utils;

import com.workFlow.WFrefactoring.document.dto.AttachmentServiceDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class FileManager {
    @Value("${file.upload-dir}")
    String fileDir;

    //return AttachedDTO로 바꿀거임
    public AttachmentServiceDto.UploadAttachment uploadFile(List<MultipartFile> multipartFileList) throws IOException {
        List<String> originalFilenameList = new ArrayList<>();
        List<String> storeFilenamList = new ArrayList<>();
        List<Long> fileSizeList = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFileList) {
            String originalFilename = multipartFile.getOriginalFilename();
            String storeFilename = CreateFileName(originalFilename);
            multipartFile.transferTo(new File(CreatePath(storeFilename)));

            originalFilenameList.add(originalFilename);
            storeFilenamList.add(CreateFileName(storeFilename));
            fileSizeList.add(multipartFile.getSize());
        }

        return AttachmentServiceDto.UploadAttachment.builder()
                .orgFileNameList(originalFilenameList)
                .fileNameList(storeFilenamList)
                .fileSizeList(fileSizeList)
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
