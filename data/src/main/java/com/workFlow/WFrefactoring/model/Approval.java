package com.workFlow.WFrefactoring.model;

import com.workFlow.WFrefactoring.enums.ApprovalProgress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="approval")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Approval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="app_no")
    private Long appNo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approval_emp_no")
    private Employee approver;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doc_no")
    private Document document;
    private int levelNo;
    @Enumerated(EnumType.STRING)
    private ApprovalProgress approval;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;



}
