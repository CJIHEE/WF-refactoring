package com.workFlow.WFrefactoring.model;

import com.workFlow.WFrefactoring.enums.ApprovalProgress;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="approval")
@Getter
public class Approval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="app_no")
    private Long appNo;
    @ManyToOne
    @JoinColumn(name = "approval_emp_no")
    private Employee approver;
    @ManyToOne
    @JoinColumn(name = "doc_no")
    private Document document;
    private int levelNo;
    private ApprovalProgress approval;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;



}