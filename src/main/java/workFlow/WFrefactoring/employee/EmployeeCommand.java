package workFlow.WFrefactoring.employee;

import lombok.Data;
import workFlow.WFrefactoring.enums.EmpStatus;
import workFlow.WFrefactoring.enums.Gender;
import workFlow.WFrefactoring.enums.Position;
import workFlow.WFrefactoring.model.Dept;
import workFlow.WFrefactoring.model.Employee;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

//입력 받은 데이터
@Data
public class EmployeeCommand {
    @Data
    public static class CreateEmployee {
        @NotNull
        private int deptNo;
        @NotNull
        private Position position;
        @NotBlank
        private String name;
        @Email
        private String mail;
        @NotBlank
        private String pw;
        @NotBlank
        private String hireDate;
        @NotNull
        private Gender gender;
        @NotBlank
        private String  phone;
        @NotBlank
        private String addr;
        @NotNull
        private EmpStatus empStatus;

        //입력받은 내부 클래스의 필드 값을 entity에 주입
        public Employee toEmployee(Dept dept){
            Employee employee = new Employee();
            employee.setDept(dept);
            employee.setPosition(position);
            employee.setName(name);
            employee.setMail(mail);
            employee.setPw(pw);
            employee.setHireDate(hireDate);
            employee.setGender(gender);
            employee.setPhone(phone);
            employee.setAddr(addr);
            employee.setEmpStatus(empStatus);
            return employee;
        }
    }
}
