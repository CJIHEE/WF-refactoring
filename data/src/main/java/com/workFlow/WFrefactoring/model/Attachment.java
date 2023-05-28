package com.workFlow.WFrefactoring.model;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="attachment")
@Getter
public class Attachment implements Serializable {
    @Id
    @Column(name="doc_no")
    private Long docNo; //documnet에서 단방향
    private String fileName;
    private String orgFileName;
    private Integer fileSize;


}
