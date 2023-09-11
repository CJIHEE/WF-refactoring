package com.workFlow.WFrefactoring.dto;

import com.workFlow.WFrefactoring.employee.dto.EmployeeServiceDto;
import com.workFlow.WFrefactoring.enums.EmpStatus;
import com.workFlow.WFrefactoring.enums.Gender;
import com.workFlow.WFrefactoring.enums.Position;
import com.workFlow.WFrefactoring.enums.UserRole;
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

        //api 모듈 dto-> domain 모듈 dto로 변환
        public EmployeeServiceDto.CreateEmployee convertToServiceDTO(){
                return EmployeeServiceDto.CreateEmployee.builder()
                        .deptNo(this.deptNo)
                        .position(this.position)
                        .name(this.name)
                        .mail(this.mail)
                        .pw(this.pw)
                        .hireDate(this.hireDate)
                        .gender(this.gender)
                        .phone(this.phone)
                        .addr(this.addr)
                        .empStatus(this.empStatus)
                        .role(this.role)
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

        public EmployeeServiceDto.LoginEmployee convertToServiceDTO(){
            return EmployeeServiceDto.LoginEmployee.builder()
                    .mail(this.mail)
                    .pw(this.pw)
                    .build();
        }
    }


    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateEmployee{
        private String addr;
        private Position position;
        private String phone;
        private EmpStatus empStatus;

        public EmployeeServiceDto.UpdateEmployee convertToServiceDto(){
            return EmployeeServiceDto.UpdateEmployee.builder()
                    .addr(this.addr)
                    .position(this.position)
                    .phone(this.phone)
                    .build();
        }

    }

}
