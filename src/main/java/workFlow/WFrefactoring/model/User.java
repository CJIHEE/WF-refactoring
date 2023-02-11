package workFlow.WFrefactoring.model;

        import java.sql.Timestamp;
        import javax.persistence.Entity;
        import javax.persistence.Id;

        import org.hibernate.annotations.CreationTimestamp;


@Entity
public class User {

    @Id
    private String userId;
    private String password;
    private String userName;
    private String userRole;
    private String star;
    @CreationTimestamp
    private Timestamp regDt;
}
