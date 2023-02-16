package workFlow.WFrefactoring.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="Document")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int doc_no;
    @ManyToOne
    @JoinColumn(name="emp_no")
    private Employee emp_no;
    private String subject;
    private String contents;
    private String expired_at;
    private int complete;
    @CreatedDate
    private LocalDateTime create_at;
    @LastModifiedDate
    private LocalDateTime modified_at;
    @OneToMany(mappedBy="doc_no")
    private List<Attachment> attachmentList;


}
