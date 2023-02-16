package workFlow.WFrefactoring.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Attachment")
public class Attachment implements Serializable {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="doc_no")
    private Document doc_no;
    private String fileName;
    private String orgFileName;
    private int fileSize;


}
