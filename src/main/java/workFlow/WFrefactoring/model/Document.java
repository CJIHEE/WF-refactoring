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
    @Column(name="doc_no")
    private Long docNo;
    @ManyToOne
    @JoinColumn(name="emp_no")
    private Employee employee; //writeEmpNo 인지 employee인지 네이밍 헷갈림
    private String subject;
    private String contents;
    private String expiredAt;
    private int complete;
    @CreatedDate
    private LocalDateTime createAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="doc_no")
    private List<Attachment> attachmentList;


}
