package workFlow.WFrefactoring.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="Approval")
public class Approval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="app_no")
    private Long appNo;
    @ManyToOne
    @JoinColumn(name = "emp_no")
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "doc_no")
    private Document document;

    private int levelNo;
    private int approval;
    @CreatedDate
    private LocalDateTime creatAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;



}
