package com.workFlow.WFrefactoring.repository;

import com.workFlow.WFrefactoring.model.Dept;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptRepository extends JpaRepository<Dept, Integer> {
}
