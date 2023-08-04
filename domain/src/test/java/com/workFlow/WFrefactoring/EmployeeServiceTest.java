package com.workFlow.WFrefactoring;

import com.workFlow.WFrefactoring.employee.dto.EmployeeRequest;
import com.workFlow.WFrefactoring.employee.dto.EmployeeResponse;
import com.workFlow.WFrefactoring.employee.service.EmployeeService;
import com.workFlow.WFrefactoring.enums.EmpStatus;
import com.workFlow.WFrefactoring.enums.Gender;
import com.workFlow.WFrefactoring.enums.Position;
import com.workFlow.WFrefactoring.exception.CheckEmailException;
import com.workFlow.WFrefactoring.exception.DeptNotFoundException;
import com.workFlow.WFrefactoring.model.Employee;
import com.workFlow.WFrefactoring.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class
})
@SpringBootTest
@ActiveProfiles("test")
public class EmployeeServiceTest {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeSignUpService employeeSignUpService;
    @AfterEach
    public void tearDown(){
        employeeRepository.deleteAllInBatch();
    }

    @Test
    public void createEmployee() {
        //given
        Position position = Position.EMPLOYEE;
        Gender gender = Gender.FEMALE;
        EmpStatus empStatus = EmpStatus.INCUMBENT;
        EmployeeRequest.CreateEmployee request = EmployeeRequest.CreateEmployee.builder()
                .deptNo(100)
                .position(position)
                .name("TEST")
                .mail("test01@naver.com")
                .pw("Qwer1234$")
                .hireDate("2022-05-13")
                .gender(gender)
                .phone("01012341234")
                .addr("seoul")
                .empStatus(empStatus).build();

        //when
        EmployeeResponse newEmployee = employeeSignUpService.SignUpEmployee(request);

        //then
        Employee findEmployee = employeeRepository.findBymail(newEmployee.getMail());
        Assertions.assertEquals(newEmployee.getMail(), findEmployee.getMail());

    }

    @Test
    public void 부서존재유무(){
        //given
        Position position = Position.EMPLOYEE;
        Gender gender = Gender.FEMALE;
        EmpStatus empStatus = EmpStatus.INCUMBENT;
        EmployeeRequest.CreateEmployee request = EmployeeRequest.CreateEmployee.builder()
                .deptNo(1000)
                .position(position)
                .name("TEST")
                .mail("test01@naver.com")
                .pw("Qwer1234$")
                .hireDate("2022-05-13")
                .gender(gender)
                .phone("01012341234")
                .addr("seoul")
                .empStatus(empStatus).build();

        //when
        DeptNotFoundException returnStatusMessage =   Assertions.assertThrows(DeptNotFoundException.class, ()->employeeSignUpService.SignUpEmployee(request));
        Assertions.assertEquals(returnStatusMessage.getMessage(),"dept not found");

    }

    @Test
    public void 중복회원예외(){
        //given
        Position position = Position.EMPLOYEE;
        Gender gender = Gender.FEMALE;
        EmpStatus empStatus = EmpStatus.INCUMBENT;
        EmployeeRequest.CreateEmployee request = EmployeeRequest.CreateEmployee.builder()
                .deptNo(100)
                .position(position)
                .name("TEST")
                .mail("test01@naver.com")
                .pw("Qwer1234$")
                .hireDate("2022-05-13")
                .gender(gender)
                .phone("01012341234")
                .addr("seoul")
                .empStatus(empStatus).build();
        employeeSignUpService.SignUpEmployee(request);

        //when
        EmployeeRequest.CreateEmployee request2 = EmployeeRequest.CreateEmployee.builder()
                .deptNo(100)
                .position(position)
                .name("TEST")
                .mail("test01@naver.com")
                .pw("Qwer1234$")
                .hireDate("2022-05-13")
                .gender(gender)
                .phone("01012341234")
                .addr("seoul")
                .empStatus(empStatus).build();

        //then
        Assertions.assertThrows(CheckEmailException.class, ()->employeeSignUpService.SignUpEmployee(request2));
    }




}
