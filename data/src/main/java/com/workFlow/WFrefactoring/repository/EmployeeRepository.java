package com.workFlow.WFrefactoring.repository;

import com.workFlow.WFrefactoring.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
     boolean existsBymail(String mail);

     //아이디 찾기
     Employee findBymail(String mail);

     //전체 회원조회
     Page<Employee> findAll(Pageable pageable);



}
