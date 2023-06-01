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


}
