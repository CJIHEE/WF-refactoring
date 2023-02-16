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
    private int emp_no;
    @ManyToOne
    @JoinColumn(name = "dept_no")
    private Dept dept;
    private String position;
    private String name;
    private String mail;
    private int pw;
    private String hitrdateAt;
    private String gender;
    private String retirementAt;
    private int phone;
    private String addr;
    private String emp_status;
    @OneToMany(mappedBy = "emp_no")
    private List<Document> documentList;

}
