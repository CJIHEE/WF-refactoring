package workFlow.WFrefactoring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import workFlow.WFrefactoring.employee.EmployeeRepository;
import workFlow.WFrefactoring.employee.EmployeeRequset;
import workFlow.WFrefactoring.employee.EmployeeResponse;
import workFlow.WFrefactoring.employee.EmployeeService;
import workFlow.WFrefactoring.enums.EmpStatus;
import workFlow.WFrefactoring.enums.Gender;
import workFlow.WFrefactoring.enums.Position;
import workFlow.WFrefactoring.exception.CheckEmailException;
import workFlow.WFrefactoring.exception.DeptNotFoundException;
import workFlow.WFrefactoring.model.Employee;

import javax.transaction.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class EmployeeServiceTest {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Transactional
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
        Employee findEmployee = employeeRepository.findBymail(newEmployee.getMail());
        Assertions.assertEquals(newEmployee.getMail(), findEmployee.getMail());

    }

    @Transactional
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

    @Transactional
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
