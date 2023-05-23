package com.workFlow.WFrefactoring;

import com.workFlow.WFrefactoring.employee.EmployeeServiceTest;
import com.workFlow.WFrefactoring.employee.dto.EmployeeRequset;
import com.workFlow.WFrefactoring.employee.dto.EmployeeResponse;
import com.workFlow.WFrefactoring.employee.EmployeeService;
import com.workFlow.WFrefactoring.enums.EmpStatus;
import com.workFlow.WFrefactoring.enums.Gender;
import com.workFlow.WFrefactoring.enums.Position;
import com.workFlow.WFrefactoring.exception.CheckEmailException;
import com.workFlow.WFrefactoring.exception.DeptNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class
})
@SpringBootTest
public class TestEmployeeService {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeServiceTest employeeServiceTest;
    @AfterEach
    public void tearDown(){
        employeeServiceTest.clear();
    }

    @Test
    public void createEmployee() {
        //given
        Position position = Position.EMPLOYEE;
        Gender gender = Gender.FEMALE;
        EmpStatus empStatus = EmpStatus.INCUMBENT;
        EmployeeRequset.CreateEmployee request = EmployeeRequset.CreateEmployee.builder()
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
        EmployeeResponse newEmployee = employeeService.createEmployee(request);

        //then
        String findEmployee = employeeServiceTest.findBymail(newEmployee.getMail());
        Assertions.assertEquals(newEmployee.getMail(), findEmployee);

    }

    @Test
    public void 부서존재유무(){
        //given
        Position position = Position.EMPLOYEE;
        Gender gender = Gender.FEMALE;
        EmpStatus empStatus = EmpStatus.INCUMBENT;
        EmployeeRequset.CreateEmployee request = EmployeeRequset.CreateEmployee.builder()
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
        DeptNotFoundException returnStatusMessage =   Assertions.assertThrows(DeptNotFoundException.class, ()->employeeService.createEmployee(request));
        Assertions.assertEquals(returnStatusMessage.getMessage(),"dept not found");

    }

    @Test
    public void 중복회원예외(){
        //given
        Position position = Position.EMPLOYEE;
        Gender gender = Gender.FEMALE;
        EmpStatus empStatus = EmpStatus.INCUMBENT;
        EmployeeRequset.CreateEmployee request = EmployeeRequset.CreateEmployee.builder()
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
        employeeService.createEmployee(request);

        //when
        EmployeeRequset.CreateEmployee request2 = EmployeeRequset.CreateEmployee.builder()
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
        Assertions.assertThrows(CheckEmailException.class, ()->employeeService.createEmployee(request2));
    }




}
