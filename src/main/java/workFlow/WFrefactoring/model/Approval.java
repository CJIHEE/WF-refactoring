package workFlow.WFrefactoring.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import workFlow.WFrefactoring.enums.ApprovalProgress;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="approval")
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
