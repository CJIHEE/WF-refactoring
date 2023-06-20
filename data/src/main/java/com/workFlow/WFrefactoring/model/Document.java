package com.workFlow.WFrefactoring.model;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="document")
@Getter
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="doc_no")
    private Long docNo;
    @ManyToOne
    @JoinColumn(name="write_emp_no")
    private Employee requester;
    private String subject;
    private String contents;
    private String expiredAt;
    private boolean complete;
    @CreatedDate
    private LocalDateTime createAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="doc_no")
    private List<Attachment> attachmentList;


}
