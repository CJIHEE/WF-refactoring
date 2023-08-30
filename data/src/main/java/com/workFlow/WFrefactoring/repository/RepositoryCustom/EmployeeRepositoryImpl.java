package com.workFlow.WFrefactoring.repository.RepositoryCustom;

import com.querydsl.core.types.dsl.BooleanExpression;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.workFlow.WFrefactoring.model.Employee;
import com.workFlow.WFrefactoring.model.QEmployee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepositoryCustom{
    private final JPAQueryFactory queryFactory;
    @Override
    public List<Employee> findEmpAll(Long lastEmpNo, Integer pageSize) {
        QEmployee employee = QEmployee.employee;
        return queryFactory
                .selectFrom(employee)
                .where(ltEmpNo(lastEmpNo))
                .limit(pageSize)
                .fetch();
    }

    private BooleanExpression ltEmpNo(Long lastEmpNo){
        QEmployee employee = QEmployee.employee;
        return lastEmpNo == null ? null : employee.empNo.gt(lastEmpNo);
    }
}
