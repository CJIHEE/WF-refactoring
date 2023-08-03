package com.workFlow.WFrefactoring.employee.dto;

import com.workFlow.WFrefactoring.enums.EmpStatus;
import com.workFlow.WFrefactoring.enums.Gender;
import com.workFlow.WFrefactoring.enums.Position;
import com.workFlow.WFrefactoring.enums.UserRole;
import com.workFlow.WFrefactoring.model.Employee;
import lombok.*;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

//입력 받은 데이터

public class EmployeeRequest {

    @Getter
    @Builder
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateEmployee {
        @NotNull
        private Integer deptNo;
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
        private String phone;
        @NotBlank
        private String addr;
        @NotNull
        private EmpStatus empStatus;
        @NotNull
        private UserRole role;

        //입력받은 내부 클래스의 필드 값을 entity에 주입(DTO->entity)
        public Employee toEmployee(Integer dept, String passWord){
            return Employee.builder()
                    .deptNo(dept)
                    .position(position)
                    .name(name)
                    .mail(mail)
                    .pw(passWord)
                    .hireDate(hireDate)
                    .gender(gender)
                    .phone(phone)
                    .addr(addr)
                    .empStatus(empStatus)
                    .role(role)
                    .build();
        }
    }
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LoginEmployee {
        private String mail;
        private String pw;
    }

    @Getter
    @AllArgsConstructor
    public static class LogoutEmployee{
        private String mail;
        private String pw;
    }



}
