package com.workFlow.WFrefactoring.employee.service;

import com.workFlow.WFrefactoring.dept.dto.DeptDto;
import com.workFlow.WFrefactoring.dept.service.DeptService;
import com.workFlow.WFrefactoring.employee.dto.EmployeeRequest;
import com.workFlow.WFrefactoring.employee.dto.EmployeeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;


@Service
@RequiredArgsConstructor
public class EmployeeSignUpService {
    private final DeptService deptService;
    private final EmployeeService employeeService;

    @Transactional
    //회원가입
    public EmployeeResponse SignUpEmployee(EmployeeRequest.@Valid CreateEmployee request) {
        //dept 여부
        DeptDto deptDto = deptService.findBydeptNo(request.getDeptNo());

        EmployeeResponse employeeResponse = employeeService.createEmployee(request, deptDto);

        return employeeResponse;
    }

}
