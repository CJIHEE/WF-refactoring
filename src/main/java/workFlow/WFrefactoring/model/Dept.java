package workFlow.WFrefactoring.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="dept")
@Data
public class Dept {
    @Id
    @Column(name="dept_no")
    private Integer deptNo;
    private String deptName;
    private Long leadEmpNo;
    private Integer upperDeptNo;

}
