package workFlow.WFrefactoring.employee;

import lombok.Getter;
import workFlow.WFrefactoring.enums.EmpStatus;
import workFlow.WFrefactoring.enums.Gender;
import workFlow.WFrefactoring.enums.Position;
import workFlow.WFrefactoring.model.Employee;

@Getter
public class EmployeeResponse {
    private Integer deptNo;
    private Long empNo;
    private Position position;
    private String name;
    private String mail;
    private String hireDate;
    private Gender gender;
    private String retirementDate;
    private String  phone;
    private String addr;
    private EmpStatus empStatus;

    //외부 클래스 필드에 입력해주는 작업(entity 값을)
    public static EmployeeResponse toVO(Employee employee) {
        EmployeeResponse employeeVO = new EmployeeResponse();
        employeeVO.deptNo = employee.getDeptNo();
        employeeVO.empNo = employee.getEmpNo();
        employeeVO.position = employee.getPosition();
        employeeVO.name = employee.getName();
        employeeVO.mail = employee.getMail();
        employeeVO.hireDate = employee.getHireDate();
        employeeVO.gender = employee.getGender();
        employeeVO.retirementDate = employee.getRetirementDate();
        employeeVO.phone = employee.getPhone();
        employeeVO.addr = employee.getAddr();
        employeeVO.empStatus = employee.getEmpStatus();
        return employeeVO;
    }




}

