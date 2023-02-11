package workFlow.WFrefactoring.model;

        import java.sql.Timestamp;
        import java.time.LocalDateTime;
        import javax.persistence.Entity;
        import javax.persistence.Id;

        import org.hibernate.annotations.CreationTimestamp;


@Entity
public class User {

    @Id
    private int emp_no;
    private int dept_no;
    private String position;
    private String name;
    private String mail;
    private int pw;
    private String hitrdate_at;
    private String gender;
    private LocalDateTime retirement_at;
    private int phone;
    private String addr;
    private String emp_status;


    @CreationTimestamp
    private Timestamp regDt;
}
