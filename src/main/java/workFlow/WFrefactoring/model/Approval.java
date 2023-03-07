package workFlow.WFrefactoring.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import workFlow.WFrefactoring.enums.Progress;

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
    @JoinColumn(name = "write_emp_no")
    private Employee requester;
    @ManyToOne
    @JoinColumn(name = "doc_no")
    private Document document;
    private int levelNo;
    private Progress approval;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;



}
