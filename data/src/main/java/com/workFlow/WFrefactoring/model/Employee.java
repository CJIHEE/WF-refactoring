package com.workFlow.WFrefactoring.model;

import com.workFlow.WFrefactoring.enums.EmpStatus;
import com.workFlow.WFrefactoring.enums.Gender;
import com.workFlow.WFrefactoring.enums.Position;
import com.workFlow.WFrefactoring.enums.UserRole;
import com.workFlow.WFrefactoring.vo.EmployeeVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;


import javax.persistence.*;


@Builder
@Entity
@Table(name="employee", indexes = @Index(name="idx_mail", columnList = "mail"))
@Getter
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="emp_no")
    private Long empNo;
    private Integer deptNo;
    @Enumerated(EnumType.STRING)
    private Position position;
    private String name;
    private String mail;
    private String pw;
    private String hireDate;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String retirementDate;
    private String  phone;
    private String addr;
    @Enumerated(EnumType.STRING)
    private EmpStatus empStatus;
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public void updateEmployee(EmployeeVO employeeVO){
        //null 인경우 이전의 값 그대로 유지
        if (employeeVO.getAddr() != null) {
            this.addr = employeeVO.getAddr();
        }
        if (employeeVO.getPosition() != null) {
            this.position = employeeVO.getPosition();
        }
        if (employeeVO.getPhone() != null) {
            this.phone = employeeVO.getPhone();
        }
        if (employeeVO.getEmpStatus() != null) {
            this.empStatus = employeeVO.getEmpStatus();
        }
    }

}
