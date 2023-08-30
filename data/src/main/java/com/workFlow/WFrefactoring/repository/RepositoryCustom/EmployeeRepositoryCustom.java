package com.workFlow.WFrefactoring.repository.RepositoryCustom;

import com.workFlow.WFrefactoring.model.Employee;

import java.util.List;

public interface EmployeeRepositoryCustom {

    List<Employee> findEmpAll(Long lastEmpNo, Integer pageSize);
}
