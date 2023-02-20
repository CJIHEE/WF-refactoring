package workFlow.WFrefactoring.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="Employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="emp_no")
    private Long empNo;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dept_no")
    private Dept dept;
    private String position;
    private String name;
    private String mail;
    private String pw;
    private String hitrdateAt;
    private String gender;
    private String retirementAt;
    private String  phone;
    private String addr;
    private String empStatus;


}
