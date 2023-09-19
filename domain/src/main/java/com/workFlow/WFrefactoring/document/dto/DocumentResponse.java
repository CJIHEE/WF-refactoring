package com.workFlow.WFrefactoring.document.dto;

import com.workFlow.WFrefactoring.model.Attachment;
import com.workFlow.WFrefactoring.model.Document;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Slf4j
public class DocumentResponse {

    private long docNo;

    private long requester;

    private String subject;

    private String contents;

    private String expiredAt;
    private List<String> attachmentList = new ArrayList<>();

    public static DocumentResponse toVO(Document document){
        DocumentResponse documentVO= new DocumentResponse();

        documentVO.docNo = document.getDocNo();
        documentVO.requester = document.getRequester().getEmpNo();
        documentVO.subject = document.getSubject();
        documentVO.contents = document.getContents();
        documentVO.expiredAt = document.getExpiredAt();

        documentVO.attachmentList= document.getAttachmentList().stream()
                .map(i -> i.getFileName())
                .collect(Collectors.toList());

        return documentVO;
    }


}
