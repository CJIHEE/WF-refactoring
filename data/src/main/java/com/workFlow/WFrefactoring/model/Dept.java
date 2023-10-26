package com.workFlow.WFrefactoring.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dept")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Dept {
    @Id
    @Column(name="dept_no")
    private Integer deptNo;
    private String deptName;
    private Long leadEmpNo;
    private Integer upperDeptNo;

    @Builder
    public Dept(Integer deptNo, String deptName, Long leadEmpNo, Integer upperDeptNo) {
        this.deptNo = deptNo;
        this.deptName = deptName;
        this.leadEmpNo = leadEmpNo;
        this.upperDeptNo = upperDeptNo;
    }
}
