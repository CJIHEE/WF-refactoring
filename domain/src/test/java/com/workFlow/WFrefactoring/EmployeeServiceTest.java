package com.workFlow.WFrefactoring;

import com.workFlow.WFrefactoring.employee.dto.EmployeeRequest;
import com.workFlow.WFrefactoring.employee.dto.EmployeeResponse;
import com.workFlow.WFrefactoring.employee.service.EmployeeService;
import com.workFlow.WFrefactoring.enums.EmpStatus;
import com.workFlow.WFrefactoring.enums.Gender;
import com.workFlow.WFrefactoring.enums.Position;
import com.workFlow.WFrefactoring.enums.UserRole;
import com.workFlow.WFrefactoring.exception.CheckEmailException;
import com.workFlow.WFrefactoring.exception.DeptNotFoundException;
import com.workFlow.WFrefactoring.model.Employee;
import com.workFlow.WFrefactoring.repository.EmployeeRepository;
import com.workFlow.WFrefactoring.security.config.JwtTokenProvider;
import com.workFlow.WFrefactoring.security.config.RedisConfig;
import com.workFlow.WFrefactoring.security.config.WebSecurityConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
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
    @MockBean
    private RedisTemplate<String, Object> redisTemplate;
    @MockBean
    private UserDetailsService userDetailsService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;
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
        UserRole userRole = UserRole.USER;
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
                .empStatus(empStatus)
                .role(userRole).build();

        //when
        EmployeeResponse newEmployee = employeeService.createEmployee(request);

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
        UserRole userRole = UserRole.USER;
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
                .empStatus(empStatus)
                .role(userRole).build();

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
        UserRole userRole = UserRole.USER;
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
                .empStatus(empStatus)
                .role(userRole).build();
        employeeService.createEmployee(request);

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
        Assertions.assertThrows(CheckEmailException.class, ()->employeeService.createEmployee(request2));
    }




}
