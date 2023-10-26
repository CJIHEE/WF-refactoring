package com.workFlow.WFrefactoring.repository;

import com.workFlow.WFrefactoring.model.Dept;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DeptRepository extends JpaRepository<Dept, Integer> {

    List<Dept> findAllByOrderByDeptNoDesc();
}
