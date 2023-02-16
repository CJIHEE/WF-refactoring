package workFlow.WFrefactoring.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Dept")
public class Dept {
    @Id
    private int dept_no;
    private String dept_name;
    private int lead_emp_no;
    private int upper_dept_no;


}
