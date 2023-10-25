package com.workFlow.WFrefactoring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="document", indexes = @Index(name="idx_requester", columnList = "write_emp_no"))
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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
    @JsonIgnore
    @OneToMany(mappedBy="document" ,cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Attachment> attachmentList = new ArrayList<>();


}
