package workFlow.WFrefactoring.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="attachment")
public class Attachment implements Serializable {
    @Id
    @Column(name="doc_no")
    private Long docNo; //documnet에서 단방향
    private String fileName;
    private String orgFileName;
    private Integer fileSize;


}
