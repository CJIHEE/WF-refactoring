package com.workFlow.WFrefactoring;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

//@TestExecutionListeners({
//        DependencyInjectionTestExecutionListener.class,
//        DirtiesContextTestExecutionListener.class
//})
@SpringBootTest
@ActiveProfiles("test")
public class EmployeeServiceTest {
//    @MockBean
//    private RedisTemplate<String, Object> redisTemplate;
//    @MockBean
//    private UserDetailsService userDetailsService;
//    @MockBean
//    private PasswordEncoder passwordEncoder;
//    @Autowired
//    private EmployeeService employeeService;
//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    public EmployeeServiceTest() {
//    }
//
//    @AfterEach
//    public void tearDown(){
//        employeeRepository.deleteAllInBatch();
//    }
//
//    @Test
//    public void createEmployee() {
//        //given
//        Position position = Position.EMPLOYEE;
//        Gender gender = Gender.FEMALE;
//        EmpStatus empStatus = EmpStatus.INCUMBENT;
//        UserRole userRole = UserRole.USER;
//        EmployeeServiceDto.CreateEmployee request = EmployeeServiceDto.CreateEmployee.builder()
//                .deptNo(100)
//                .position(position)
//                .name("TEST")
//                .mail("test01@naver.com")
//                .pw("Qwer1234$")
//                .hireDate("2022-05-13")
//                .gender(gender)
//                .phone("01012341234")
//                .addr("seoul")
//                .empStatus(empStatus)
//                .role(userRole).build();
//
//        //when
//        EmployeeResponse newEmployee = employeeService.createEmployee(request);
//
//        //then
//        Employee findEmployee = employeeRepository.findBymail(newEmployee.getMail());
//        assertEquals(newEmployee.getMail(), findEmployee.getMail());
//
//    }
//
//    @Test
//    public void 부서존재유무(){
//        //given
//        Position position = Position.EMPLOYEE;
//        Gender gender = Gender.FEMALE;
//        EmpStatus empStatus = EmpStatus.INCUMBENT;
//        UserRole userRole = UserRole.USER;
//        EmployeeServiceDto.CreateEmployee request = EmployeeServiceDto.CreateEmployee.builder()
//                .deptNo(1000)
//                .position(position)
//                .name("TEST")
//                .mail("test01@naver.com")
//                .pw("Qwer1234$")
//                .hireDate("2022-05-13")
//                .gender(gender)
//                .phone("01012341234")
//                .addr("seoul")
//                .empStatus(empStatus)
//                .role(userRole).build();
//
//        //when
//        DeptNotFoundException returnStatusMessage =   Assertions.assertThrows(DeptNotFoundException.class, ()->employeeService.createEmployee(request));
//        assertEquals(returnStatusMessage.getMessage(),"dept not found");
//
//    }
//
//    @Test
//    public void 중복회원예외(){
//        //given
//        Position position = Position.EMPLOYEE;
//        Gender gender = Gender.FEMALE;
//        EmpStatus empStatus = EmpStatus.INCUMBENT;
//        UserRole userRole = UserRole.USER;
//        EmployeeServiceDto.CreateEmployee request = EmployeeServiceDto.CreateEmployee.builder()
//                .deptNo(100)
//                .position(position)
//                .name("TEST")
//                .mail("test01@naver.com")
//                .pw("Qwer1234$")
//                .hireDate("2022-05-13")
//                .gender(gender)
//                .phone("01012341234")
//                .addr("seoul")
//                .empStatus(empStatus)
//                .role(userRole).build();
//        employeeService.createEmployee(request);
//
//        //when
//        EmployeeServiceDto.CreateEmployee request2 = EmployeeServiceDto.CreateEmployee.builder()
//                .deptNo(100)
//                .position(position)
//                .name("TEST")
//                .mail("test01@naver.com")
//                .pw("Qwer1234$")
//                .hireDate("2022-05-13")
//                .gender(gender)
//                .phone("01012341234")
//                .addr("seoul")
//                .empStatus(empStatus).build();
//
//        //then
//        Assertions.assertThrows(CheckEmailException.class, ()->employeeService.createEmployee(request2));
//    }
//
//    @Test
//    public void 회원정보변경(){
//
//        //given
//        Position position = Position.EMPLOYEE;
//        Gender gender = Gender.FEMALE;
//        EmpStatus empStatus = EmpStatus.INCUMBENT;
//        UserRole userRole = UserRole.USER;
//        EmployeeServiceDto.CreateEmployee request = EmployeeServiceDto.CreateEmployee.builder()
//                .deptNo(100)
//                .position(position)
//                .name("TEST")
//                .mail("test01@naver.com")
//                .pw("Qwer1234$")
//                .hireDate("2022-05-13")
//                .gender(gender)
//                .phone("01012341234")
//                .addr("seoul")
//                .empStatus(empStatus)
//                .role(userRole).build();
//        employeeService.createEmployee(request);
//
//        //when
//        EmployeeServiceDto.UpdateEmployee updateEmployee = EmployeeServiceDto.UpdateEmployee.builder()
//                .position(Position.DIRECTOR)
//                .phone("01012341234")
//                .addr("change")
//                .empStatus(empStatus).build();
//
//        Employee employee= employeeRepository.findBymail(request.getMail());
//
//        EmployeeDetails employeeDetails = new EmployeeDetails(employee);
//
//        EmployeeResponse response = employeeService.updateEmployee(employee.getEmpNo(), updateEmployee,employeeDetails);
//
//        //then
//        assertNotNull(response);
//        assertEquals(updateEmployee.getAddr(), response.getAddr());
//        assertEquals(updateEmployee.getPosition(), response.getPosition());
//        assertEquals(updateEmployee.getPhone(), response.getPhone());
//        assertEquals(updateEmployee.getEmpStatus(), response.getEmpStatus());
//
//    }
//
}
