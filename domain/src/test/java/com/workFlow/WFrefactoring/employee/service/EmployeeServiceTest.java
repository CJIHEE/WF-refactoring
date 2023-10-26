package com.workFlow.WFrefactoring.employee.service;

import com.workFlow.WFrefactoring.ServiceLayerTestSupport;
import com.workFlow.WFrefactoring.dept.dto.DeptDto;
import com.workFlow.WFrefactoring.dept.service.DeptService;
import com.workFlow.WFrefactoring.employee.dto.EmployeeResponse;
import com.workFlow.WFrefactoring.employee.dto.EmployeeServiceDto;
import com.workFlow.WFrefactoring.enums.*;
import com.workFlow.WFrefactoring.exception.CheckEmailException;
import com.workFlow.WFrefactoring.model.Employee;
import com.workFlow.WFrefactoring.repository.EmployeeRepository;
import com.workFlow.WFrefactoring.security.domain.EmployeeDetails;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

public class EmployeeServiceTest extends ServiceLayerTestSupport {
    @MockBean
    private DeptService deptService;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeService employeeService;

    @AfterEach
    public void tearDown(){
        employeeRepository.deleteAllInBatch();
    }

    @Test
    public void 회원가입_성공() {
        //given
        Position position = Position.EMPLOYEE;
        Gender gender = Gender.FEMALE;
        EmpStatus empStatus = EmpStatus.INCUMBENT;
        UserRole userRole = UserRole.USER;

        DeptDto dept = DeptDto.builder()
                .deptNo(1)
                .deptName("test")
                .leadEmpNo(1L)
                .upperDeptNo(1)
                .build();

        // stubbing
        given(deptService.findBydeptNo(anyInt()))
                .willReturn(dept);

        EmployeeServiceDto.CreateEmployee request = EmployeeServiceDto.CreateEmployee.builder()
                .deptNo(100)
                .position(position)
                .name("TEST")
                .mail("test01@naver.com")
                .pw("Qwer1234$")
                .hireDate("2022-05-13")
                .gender(gender)
                .phone("01012341234")
                .addr("seoul")
                .empStatus(empStatus)
                .role(userRole).build();

        //when
        EmployeeResponse newEmployee = employeeService.createEmployee(request);

        //then
        Employee findEmployee = employeeRepository.findBymail(newEmployee.getMail());
        assertThat(newEmployee.getMail()).isEqualTo(findEmployee.getMail());
    }

    @Test
    public void 중복회원예외(){
        //given
        String mail = "testEmail@test.com";
        employeeRepository.save(Employee.builder()
                .mail(mail)
                .build());

        EmployeeServiceDto.CreateEmployee request2 = EmployeeServiceDto.CreateEmployee.builder()
                .deptNo(100)
                .position(Position.EMPLOYEE)
                .name("TEST")
                .mail(mail)
                .pw("Qwer1234$")
                .hireDate("2022-05-13")
                .gender(Gender.FEMALE)
                .phone("01012341234")
                .addr("seoul")
                .empStatus(EmpStatus.INCUMBENT)
                .build();

        //when & then
        assertThatThrownBy(() -> employeeService.createEmployee(request2))
                .isInstanceOf(CheckEmailException.class)
                .hasMessage("ID duplication");
    }

    @Test
    public void 회원정보변경(){
        //given
        Employee employee = employeeRepository.save(Employee.builder()
                .deptNo(100)
                .position(Position.EMPLOYEE)
                .name("TEST")
                .mail("test01@naver.com")
                .pw("Qwer1234$")
                .hireDate("2022-05-13")
                .gender(Gender.FEMALE)
                .phone("01012341234")
                .addr("seoul")
                .empStatus(EmpStatus.INCUMBENT)
                .role(UserRole.USER).build());

        EmployeeServiceDto.UpdateEmployee updateEmployee = EmployeeServiceDto.UpdateEmployee.builder()
                .position(Position.DIRECTOR)
                .phone("01012341234")
                .addr("change")
                .empStatus(EmpStatus.LEAVE).build();

        EmployeeDetails employeeDetails = new EmployeeDetails(employee);

        //when
        EmployeeResponse response = employeeService.updateEmployee(employee.getEmpNo(), updateEmployee, employeeDetails);

        //then
        assertThat(response).isNotNull();
        assertThat(updateEmployee.getAddr()).isEqualTo(response.getAddr());
        assertThat(updateEmployee.getPosition()).isEqualTo(response.getPosition());
        assertThat(updateEmployee.getPhone()).isEqualTo(response.getPhone());
        assertThat(updateEmployee.getEmpStatus()).isEqualTo(response.getEmpStatus());
    }

}
