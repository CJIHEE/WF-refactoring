package com.workFlow.WFrefactoring.dept.service;

import com.workFlow.WFrefactoring.dept.dto.DeptDto;
import com.workFlow.WFrefactoring.exception.DeptNotFoundException;
import com.workFlow.WFrefactoring.model.Dept;
import com.workFlow.WFrefactoring.repository.DeptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class DeptService {

   private final DeptRepository deptRepository;

    //부서 찾기
    @Transactional(readOnly = true)
    public DeptDto findBydeptNo(Integer deptNo) {
        //dept 여부
        Dept dept = deptRepository.findById(deptNo).orElseThrow(()-> new DeptNotFoundException("dept not found"));
        return DeptDto.builder()
                .deptNo(dept.getDeptNo())
                .deptName(dept.getDeptName())
                .leadEmpNo(dept.getLeadEmpNo())
                .upperDeptNo(dept.getUpperDeptNo())
                .build();
    }
}
