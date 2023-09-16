package com.workFlow.WFrefactoring.document.service;

import com.workFlow.WFrefactoring.document.dto.AttachmentServiceDto;
import com.workFlow.WFrefactoring.document.dto.DocumentResponse;
import com.workFlow.WFrefactoring.document.dto.DocumentServiceDto;
import com.workFlow.WFrefactoring.exception.UserNotFoundException;
import com.workFlow.WFrefactoring.model.Attachment;
import com.workFlow.WFrefactoring.model.Document;
import com.workFlow.WFrefactoring.model.Employee;
import com.workFlow.WFrefactoring.repository.DocumentRepository;
import com.workFlow.WFrefactoring.repository.EmployeeRepository;
import com.workFlow.WFrefactoring.utils.FileManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DocumentService {

    private final EmployeeRepository employeeRepository;
    private final DocumentRepository documentRepository;

    private final FileManager fileManager;

    @Transactional
    public DocumentResponse wrtieDocument(DocumentServiceDto.WriteDocument documentDto, AttachmentServiceDto.uploadAttachment attachmentDto) throws IOException {

        Employee employee = employeeRepository.findById(documentDto.getRequester()).orElseThrow(() -> new UserNotFoundException("user not found"));
        Document document = documentRepository.save(documentDto.toDocument(employee));

        Attachment attachment = null;
        if (attachmentDto.getMultipartFile() != null) {
            //첨부파일 있을때
            attachment = fileManager.uploadFile(attachmentDto.getMultipartFile()).toAttachment(document);
            document.getAttachmentList().add(attachment);
        }

        return DocumentResponse.toVO(document,attachment);
    }
}
