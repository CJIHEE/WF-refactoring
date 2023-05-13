package workFlow.WFrefactoring.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import workFlow.WFrefactoring.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
     boolean existsBymail(String mail);

     //아이디 찾기
     Employee findBymail(String mail);
}
