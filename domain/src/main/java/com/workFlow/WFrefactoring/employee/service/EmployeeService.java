package com.workFlow.WFrefactoring.employee.service;

import com.workFlow.WFrefactoring.dept.dto.DeptDto;
import com.workFlow.WFrefactoring.employee.dto.EmployeeRequset;
import com.workFlow.WFrefactoring.employee.dto.EmployeeResponse;
import com.workFlow.WFrefactoring.exception.CheckEmailException;
import com.workFlow.WFrefactoring.model.Employee;
import com.workFlow.WFrefactoring.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import javax.validation.Valid;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    //회원가입
    @Transactional
    public EmployeeResponse createEmployee(EmployeeRequset.CreateEmployee request) {
        //ID존재 유무, 존재 유무라서 exists 사용(boolean 타입)
        if(employeeRepository.existsBymail(request.getMail())){
            throw new CheckEmailException("ID duplication");
        }
        //DB에 저장(save),command로받은 데이터 entity에 데이터 주입(DTO->entity)
        Employee employee = employeeRepository.save(request.toEmployee(request.getDeptNo()));
        //entity 값 VO에 주입
        return EmployeeResponse.toVO(employee);
    }
}