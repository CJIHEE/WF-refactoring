package com.workFlow.WFrefactoring.employee;

import com.workFlow.WFrefactoring.employee.dto.EmployeeRequset;
import com.workFlow.WFrefactoring.employee.dto.EmployeeResponse;
import com.workFlow.WFrefactoring.employee.service.EmployeeService;
import com.workFlow.WFrefactoring.employee.service.EmployeeSignUpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("employees")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeSignUpService employeeSignUpService;
    @PostMapping
    //command 입력받은 데이터
    public EmployeeResponse createEmployee(@RequestBody @Valid EmployeeRequset.CreateEmployee request){
        log.info("CreateEmployee={}", request);
        return employeeSignUpService.SignUpEmployee(request);
    }
    @GetMapping
    public void test(){
       log.info("testSuccess");
    }



}

