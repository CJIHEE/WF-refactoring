package workFlow.WFrefactoring.model;

import org.hibernate.annotations.CreationTimestamp;
import workFlow.WFrefactoring.enums.EmpStatus;
import workFlow.WFrefactoring.enums.Gender;
import workFlow.WFrefactoring.enums.Position;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="emp_no")
    private Long empNo;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dept_no")
    private Dept dept;
    private Position position;
    private String name;
    private String mail;
    private String pw;
    private String hireDate;
    private Gender gender;
    private String retirementDate;
    private String  phone;
    private String addr;
    private EmpStatus empStatus;


}