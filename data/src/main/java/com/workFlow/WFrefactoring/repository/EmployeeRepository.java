package com.workFlow.WFrefactoring.repository;

import com.workFlow.WFrefactoring.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
     boolean existsBymail(String mail);

     //아이디 찾기
     Employee findBymail(String mail);


}
