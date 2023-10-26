package com.workFlow.WFrefactoring.vo;

import com.workFlow.WFrefactoring.enums.EmpStatus;
import com.workFlow.WFrefactoring.enums.Position;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
//@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmployeeVO {
    private String addr;
    private Position position;
    private String phone;
    private EmpStatus empStatus;

    private EmployeeVO(String addr, Position position, String phone, EmpStatus empStatus) {
        this.addr = addr;
        this.position = position;
        this.phone = phone;
        this.empStatus = empStatus;
    }

    public static EmployeeVO toEmployeeVO(String addr, Position position,
                                          String phone, EmpStatus empStatus){
        return new EmployeeVO(addr, position, phone, empStatus);
    }
}
