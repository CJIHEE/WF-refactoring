package workFlow.WFrefactoring.employee;

import lombok.Data;
import lombok.Getter;
import workFlow.WFrefactoring.enums.EmpStatus;
import workFlow.WFrefactoring.enums.Gender;
import workFlow.WFrefactoring.enums.Position;
import workFlow.WFrefactoring.model.Dept;
import workFlow.WFrefactoring.model.Employee;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

//입력 받은 데이터
@Getter
public class EmployeeRequset {
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
            return Employee.builder()
                    .dept(dept)
                    .position(position)
                    .name(name)
                    .mail(mail)
                    .pw(pw)
                    .hireDate(hireDate)
                    .gender(gender)
                    .phone(phone)
                    .addr(addr)
                    .empStatus(empStatus)
                    .build();
        }
    }
}
