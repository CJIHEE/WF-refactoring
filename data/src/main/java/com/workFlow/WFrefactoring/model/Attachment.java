package com.workFlow.WFrefactoring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="attachment",indexes = @Index(name="idx_document", columnList = "doc_no"))
@Getter
@NoArgsConstructor
public class Attachment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attachmentNo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="doc_no")
    private Document document;
    private String fileName;
    private String orgFileName;
    private Long fileSize;

    @Builder
    public Attachment(Document document, String fileName, String orgFileName, Long fileSize) {
        this.document = document;
        this.fileName = fileName;
        this.orgFileName = orgFileName;
        this.fileSize = fileSize;
    }
}
