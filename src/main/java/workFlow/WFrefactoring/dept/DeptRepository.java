package workFlow.WFrefactoring.dept;

import org.springframework.data.jpa.repository.JpaRepository;
import workFlow.WFrefactoring.model.Dept;

import java.util.Optional;

public interface DeptRepository extends JpaRepository<Dept, Integer> {

    Optional<Dept> findBydeptNo(int deptNo);
}
