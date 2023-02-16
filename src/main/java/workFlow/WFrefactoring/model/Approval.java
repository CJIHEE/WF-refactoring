package workFlow.WFrefactoring.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name="approval")
public class Approval {
    @Id
    private int app_no;
    private int approval_emp_no;
    private int doc_no;
    private int levelno;
    private int approval;
    @CreatedDate
    private LocalDateTime creat_at;
    @LastModifiedDate
    private LocalDateTime modified_at;


}
