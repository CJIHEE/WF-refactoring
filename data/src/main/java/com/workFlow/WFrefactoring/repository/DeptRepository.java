package com.workFlow.WFrefactoring.repository;

import com.workFlow.WFrefactoring.model.Dept;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface DeptRepository extends JpaRepository<Dept, Integer> {

    Optional<Dept> findAllBydeptNo(Integer deptNo);


}
