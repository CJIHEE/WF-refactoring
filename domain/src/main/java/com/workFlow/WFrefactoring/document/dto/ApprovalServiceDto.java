package com.workFlow.WFrefactoring.document.dto;

import com.workFlow.WFrefactoring.enums.ApprovalProgress;
import com.workFlow.WFrefactoring.model.Approval;
import com.workFlow.WFrefactoring.model.Document;
import com.workFlow.WFrefactoring.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

public class ApprovalServiceDto {
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor

    public static class CreateApproval{
        private Long appNo;

        private Employee approver;

        private Document document;
        private int levelNo;
        private ApprovalProgress approval;

        private LocalDateTime createdAt;

        private LocalDateTime modifiedAt;

        public Approval toApproval(Employee employee, Document document){
            return Approval.builder()
                    .approver(employee)
                    .document(document)
                    .build();
        }

    }




}
