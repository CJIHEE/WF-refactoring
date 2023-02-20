package workFlow.WFrefactoring.model;

import javax.persistence.*;

@Entity
@Table(name="Dept")
public class Dept {
    @Id
    @Column(name="dept_no")
    private int deptNo;
    private String deptName;
    private int leadEmpNo;
    private int upperDeptNo;


}
