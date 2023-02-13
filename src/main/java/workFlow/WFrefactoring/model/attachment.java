package workFlow.WFrefactoring.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="attachment")
public class attachment {
    @Id
    private int doc_no;
    private String fileName;
    private String orgFileName;
    private int fileSize;


}
