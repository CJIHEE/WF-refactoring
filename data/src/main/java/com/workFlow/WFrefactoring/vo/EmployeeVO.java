package com.workFlow.WFrefactoring.vo;

import com.workFlow.WFrefactoring.enums.EmpStatus;
import com.workFlow.WFrefactoring.enums.Position;
import lombok.Getter;

@Getter
public class EmployeeVO {
    private String addr;
    private Position position;
    private String phone;
    private EmpStatus empStatus;

    public static EmployeeVO toEmployeeVO(String addr, Position position,
                                          String phone, EmpStatus empStatus){
        EmployeeVO employeeVO = new EmployeeVO();
        employeeVO.addr = addr;
        employeeVO.position = position;
        employeeVO.phone = phone;
        employeeVO.empStatus = empStatus;

        return employeeVO;
    }
}
