package com.workFlow.WFrefactoring.repository.RepositoryCustom;

import com.workFlow.WFrefactoring.model.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeptNativeRepository extends JpaRepository<Dept, Long>  {

}
