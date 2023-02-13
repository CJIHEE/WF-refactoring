package workFlow.WFrefactoring.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name="employee")
public class Employee {
    @Id
    private int emp_no;
    private int dept_no;
    private String position;
    private String name;
    private String mail;
    private int pw;
    private String hitrdate_at;
    private String gender;
    private String retirement_at;
    private int phone;
    private String addr;
    private String emp_status;

}
