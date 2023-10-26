package com.workFlow.WFrefactoring.repository.RepositoryCustom;

import com.workFlow.WFrefactoring.model.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeptNativeRepository extends JpaRepository<Dept, Long>  {
//    @Query(value = "WITH RECURSIVE DepartmentHierarchy AS ("
//            + "SELECT dept_no, dept_name, upper_dept_no, lead_emp_no "
//            + "FROM Dept "
//            + "WHERE dept_no = :deptNo "
//            + "UNION ALL "
//            + "SELECT d.dept_no, d.dept_name, d.upper_dept_no, d.lead_emp_no "
//            + "FROM Dept AS d "
//            + "JOIN DepartmentHierarchy AS dh ON dh.upper_dept_no = d.dept_no) "
//            + "SELECT * FROM DepartmentHierarchy order by dept_no asc", nativeQuery = true)
//    List<Dept> findApprovarList(@Param("deptNo") Integer deptNo);
}
