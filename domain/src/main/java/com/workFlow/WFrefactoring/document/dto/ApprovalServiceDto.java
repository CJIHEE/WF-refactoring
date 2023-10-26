package com.workFlow.WFrefactoring.document.dto;

import com.workFlow.WFrefactoring.enums.ApprovalProgress;
import com.workFlow.WFrefactoring.model.Approval;
import com.workFlow.WFrefactoring.model.Document;
import com.workFlow.WFrefactoring.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

public class ApprovalServiceDto {
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateApproval {
        private Long appNo;

        private Employee approver;

        private Document document;
        private Integer levelNo;
        private ApprovalProgress approval;

        private LocalDateTime createdAt;

        private LocalDateTime modifiedAt;

        public Approval toApproval(Employee employee, Document document) {
            return Approval.builder()
                    .approver(employee)
                    .document(document)
                    .build();
        }
    }

        @Getter
        @Slf4j
        public static class getApproval{
            private Long appNo;
            private Long approver;
            private Integer levelNo;
            private ApprovalProgress approval;

            private LocalDateTime createdAt;

            private LocalDateTime modifiedAt;

            public ApprovalServiceDto.getApproval of(Approval approval) {
                ApprovalServiceDto.getApproval approvalDto = new ApprovalServiceDto.getApproval();
                approvalDto.appNo = approval.getAppNo();
                approvalDto.approver = approval.getApprover().getEmpNo();
                approvalDto.levelNo = approval.getLevelNo();
                approvalDto.approval = approval.getApproval();
                approvalDto.createdAt = approval.getCreatedAt();
                approvalDto.modifiedAt = approval.getModifiedAt();
                return  approvalDto;

            }

    }


}
