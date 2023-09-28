package com.workFlow.WFrefactoring.document.dto;

import com.workFlow.WFrefactoring.model.Document;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public static class getDocumentListResponse{
        private long docNo;

        private long requester;

        private String subject;

        public DocumentResponse.getDocumentListResponse from(Document document){
            DocumentResponse.getDocumentListResponse documentVO = new DocumentResponse.getDocumentListResponse();

            documentVO.docNo = document.getDocNo();
            documentVO.requester = document.getRequester().getEmpNo();
            documentVO.subject = document.getSubject();

            return documentVO;
        }

    }
    @Getter
    public static class getDocumentResponse{
        private long docNo;

        private long requester;

        private String subject;

        private String contents;

        private String expiredAt;
        private LocalDateTime createdAt;
        private List<String> attachmentList = new ArrayList<>();
        private List<ApprovalServiceDto.getApproval> approvalList;

//        private List<Approval> approvalList = new ArrayList<>();
        private LocalDateTime modifiedAt;

        public DocumentResponse.getDocumentResponse from(Document document, List<String> attachmentList, List<ApprovalServiceDto.getApproval> ApprovalList){
            DocumentResponse.getDocumentResponse documentVO= new DocumentResponse.getDocumentResponse();

            documentVO.docNo = document.getDocNo();
            documentVO.requester = document.getRequester().getEmpNo();
            documentVO.subject = document.getSubject();
            documentVO.contents = document.getContents();
            documentVO.expiredAt = document.getExpiredAt();
            documentVO.createdAt = document.getCreateAt();
            documentVO.attachmentList= attachmentList;
            documentVO.approvalList = ApprovalList;
            documentVO.modifiedAt = document.getModifiedAt();

            return documentVO;
        }

    }


}
