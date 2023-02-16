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
    private int app_no;
    @ManyToOne
    @JoinColumn(name = "emp_no")
    private Employee approval_emp_no;
    @ManyToOne
    @JoinColumn(name = "doc_no")
    private Document document;

    private int levelno;
    private int approval;
    @CreatedDate
    private LocalDateTime creat_at;
    @LastModifiedDate
    private LocalDateTime modified_at;



}
