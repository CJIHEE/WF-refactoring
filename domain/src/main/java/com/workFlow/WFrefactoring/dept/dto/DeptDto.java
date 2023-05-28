package com.workFlow.WFrefactoring.dept.dto;

import com.workFlow.WFrefactoring.model.Dept;
import lombok.Getter;

@Getter
public class DeptDto {
    private Integer deptNo;
    private String deptName;
    private Long leadEmpNo;
    private Integer upperDeptNo;

    public static DeptDto deptEntityToDeptDto(Dept dept) {
        DeptDto deptDto = new DeptDto();
        deptDto.deptNo = dept.getDeptNo();
        deptDto.deptName = dept.getDeptName();
        deptDto.leadEmpNo = dept.getLeadEmpNo();
        deptDto.upperDeptNo = dept.getUpperDeptNo();
        return deptDto;
    }

}
