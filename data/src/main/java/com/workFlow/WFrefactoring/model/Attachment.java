package com.workFlow.WFrefactoring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="attachment")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Attachment implements Serializable {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="doc_no")
    private Document document;
    private String fileName;
    private String orgFileName;
    private Long fileSize;


}
