package com.workFlow.WFrefactoring.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class FileManager {
    private final Environment env;
    private static final String FILE_DIR = "file.upload-dir";

    public String uploadFile(MultipartFile multipartFile) {
        String originalFilename = multipartFile.getOriginalFilename();
        String storeFilename = createSaveFileName(originalFilename);

        try {
            multipartFile.transferTo(new File(createPath(storeFilename)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return storeFilename;
    }

    private String createSaveFileName(String originalFilename){
        UUID uuid = UUID.randomUUID();
        return uuid + "_" + originalFilename;
    }
    private String createPath(String fileName){
        return env.getProperty(FILE_DIR) + fileName;
    }
}
