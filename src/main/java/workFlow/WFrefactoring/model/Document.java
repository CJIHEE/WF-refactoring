package workFlow.WFrefactoring.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name="document")
public class Document {
    @Id
    private int doc_no;
    private int write_emp_no;
    private String subject;
    private String contents;
    private String expired_at;
    private int complete;
    @CreatedDate
    private LocalDateTime create_at;
    @LastModifiedDate
    private LocalDateTime modified_at;


}
