package com.workFlow.WFrefactoring.dept.dto;

import com.workFlow.WFrefactoring.model.Dept;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeptDto {
    private Integer deptNo;
    private String deptName;
    private Long leadEmpNo;
    private Integer upperDeptNo;

    public static DeptDto deptEntityToDeptDto(Dept dept){
        return DeptDto.builder()
                .deptNo(dept.getDeptNo())
                .deptName(dept.getDeptName())
                .leadEmpNo(dept.getLeadEmpNo())
                .upperDeptNo(dept.getUpperDeptNo())
                .build();
    }


}
