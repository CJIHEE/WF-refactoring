package com.workFlow.WFrefactoring.document.dto;

import com.workFlow.WFrefactoring.model.Approval;
import com.workFlow.WFrefactoring.model.Document;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
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
    private LocalDateTime createdAt;
    private List<String> attachmentList = new ArrayList<>();



    public static DocumentResponse from(Document document, List<String> attachmentList){
        DocumentResponse documentVO= new DocumentResponse();

        documentVO.docNo = document.getDocNo();
        documentVO.requester = document.getRequester().getEmpNo();
        documentVO.subject = document.getSubject();
        documentVO.contents = document.getContents();
        documentVO.expiredAt = document.getExpiredAt();
        documentVO.createdAt = document.getCreateAt();

        documentVO.attachmentList= attachmentList;

        return documentVO;
    }

    public static class getAllDocumentResponse{
        private long docNo;

        private long requester;

        private String subject;

        public DocumentResponse.getAllDocumentResponse toVO(Document document){
            DocumentResponse.getAllDocumentResponse documentVO = new DocumentResponse.getAllDocumentResponse();

            documentVO.docNo = document.getDocNo();
            documentVO.requester = document.getRequester().getEmpNo();
            documentVO.subject = document.getSubject();

            return documentVO;
        }

    }
    public static class getDocumentResponse{
        private long docNo;

        private long requester;

        private String subject;

        private String contents;

        private String expiredAt;
        private LocalDateTime createdAt;
        private List<String> attachmentList = new ArrayList<>();
        private Approval approval;
        private LocalDateTime modifiedAt;

        public DocumentResponse.getDocumentResponse toVO(Document document, List<String> attachmentList){
            DocumentResponse.getDocumentResponse documentVO= new DocumentResponse.getDocumentResponse();

            documentVO.docNo = document.getDocNo();
            documentVO.requester = document.getRequester().getEmpNo();
            documentVO.subject = document.getSubject();
            documentVO.contents = document.getContents();
            documentVO.expiredAt = document.getExpiredAt();
            documentVO.createdAt = document.getCreateAt();
            documentVO.attachmentList= attachmentList;
            documentVO.modifiedAt = document.getModifiedAt();

            return documentVO;
        }
    }


}
