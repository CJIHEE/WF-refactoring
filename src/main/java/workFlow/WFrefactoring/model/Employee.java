package workFlow.WFrefactoring.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import workFlow.WFrefactoring.employee.EmployeeRequset;
import workFlow.WFrefactoring.enums.EmpStatus;
import workFlow.WFrefactoring.enums.Gender;
import workFlow.WFrefactoring.enums.Position;

import javax.persistence.*;

@Builder
@Entity
@Table(name="employee", indexes = @Index(name="idx_mail", columnList = "mail"))
@Getter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="emp_no")
    private Long empNo;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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
