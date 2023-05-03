package workFlow.WFrefactoring.employee;

import lombok.Data;
import workFlow.WFrefactoring.enums.EmpStatus;
import workFlow.WFrefactoring.enums.Gender;
import workFlow.WFrefactoring.enums.Position;
import workFlow.WFrefactoring.model.Employee;

@Data
public class EmployeeVO {
    private int deptNo;
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
    private String message;

    //외부 클래스 필드에 입력해주는 작업(entity 값을)
    public static EmployeeVO toVO(Employee employee) {
        EmployeeVO employeeVO = new EmployeeVO();
        employeeVO.setDeptNo(employee.getDept().getDeptNo());
        employeeVO.setEmpNo(employee.getEmpNo());
        employeeVO.setPosition(employee.getPosition());
        employeeVO.setName(employee.getName());
        employeeVO.setMail(employee.getMail());
        employeeVO.setHireDate(employee.getHireDate());
        employeeVO.setGender(employee.getGender());
        employeeVO.setRetirementDate(employee.getRetirementDate());
        employeeVO.setPhone(employee.getPhone());
        employeeVO.setEmpStatus(employee.getEmpStatus());
        employeeVO.setMessage("ok");
        return employeeVO;
    }




}

