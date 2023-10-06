package com.workFlow.WFrefactoring.employee.dto;

import com.workFlow.WFrefactoring.employee.dto.EmployeeServiceDto;
import com.workFlow.WFrefactoring.enums.EmpStatus;
import com.workFlow.WFrefactoring.enums.Gender;
import com.workFlow.WFrefactoring.enums.Position;
import com.workFlow.WFrefactoring.enums.UserRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
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
    @ApiModel(description = "사원 등록 Request Parameter")
    public static class CreateEmployee {
        @ApiModelProperty(value = "부서 번호", required = true, example = "10")
        @NotNull
        private Integer deptNo;
        @ApiModelProperty(value = "사원 직급", required = true, example = "EMPLOYEE")
        @NotNull
        private Position position;
        @ApiModelProperty(value = "사원명", required = true, example = "홍길동")
        @NotBlank
        private String name;
        @ApiModelProperty(value = "사원 아이디", required = true, example = "hong12@naver.com")
        @Email
        private String mail;
        @ApiModelProperty(value = "비밀 번호", required = true, example = "1234GFDW@$")
        @NotBlank
        private String pw;
        @ApiModelProperty(value = "입사 일자", required = true, example = "2022-03-12")
        @NotBlank
        private String hireDate;
        @ApiModelProperty(value = "성별", required = true, example = "FEMALE")
        @NotNull
        private Gender gender;
        @ApiModelProperty(value = "전화 번호", required = true, example = "010-2342-1234")
        @NotBlank
        private String phone;
        @ApiModelProperty(value = "주소", required = true, example = "SEOUL")
        @NotBlank
        private String addr;
        @ApiModelProperty(value = "재직 상태", required = true, example = "INCUMBENT")
        @NotNull
        private EmpStatus empStatus;
        @ApiModelProperty(value = "관리자여부", required = true, example = "USER")
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
    @ApiModel(description = "로그인 Request Parameter")
    public static class LoginEmployee {
        @ApiModelProperty(value = "사원 아이디", required = true, example = "hong12@naver.com")
        private String mail;
        @ApiModelProperty(value = "비밀 번호", required = true, example = "5432QDFG!@")
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
    @ApiModel(description = "사원 정보 수정 Request Parameter")
    public static class UpdateEmployee{
        @ApiModelProperty(value = "주소", required = true, example = "SEOUL")
        private String addr;
        @ApiModelProperty(value = "직급", required = true, example = "DIRECTOR")
        private Position position;
        @ApiModelProperty(value = "전화 번호", required = true, example = "010-1234-1234")
        private String phone;
        @ApiModelProperty(value = "재직 상태", required = true, example = "LEAVE")
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
