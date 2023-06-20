package com.workFlow.WFrefactoring.model;

import com.workFlow.WFrefactoring.enums.EmpStatus;
import com.workFlow.WFrefactoring.enums.Gender;
import com.workFlow.WFrefactoring.enums.Position;
import com.workFlow.WFrefactoring.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;


@Builder
@Entity
@Table(name="employee", indexes = @Index(name="idx_mail", columnList = "mail"))
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="emp_no")
    private Long empNo;
    private Integer deptNo;
    private Position position;
    private String name;
    private String mail;
    private String pw;
    private String hireDate;
    private Gender gender;
    private String retirementDate;
    private String  phone;
    private String addr;
    private EmpStatus empStatus;
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.EMPLOYEE;

}
