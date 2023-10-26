package com.workFlow.WFrefactoring.dept.service;

import com.workFlow.WFrefactoring.dept.dto.DeptDto;
import com.workFlow.WFrefactoring.exception.DeptNotFoundException;
import com.workFlow.WFrefactoring.model.Dept;
import com.workFlow.WFrefactoring.repository.DeptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


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

    //부서 결제라인 만들기
    @Transactional
    public List<DeptDto> createAppDeptList(Integer deptNo) {
        List<Dept> deptList = deptRepository.findAllByOrderByDeptNoDesc();
        List<DeptDto> appDeptList = new ArrayList();
        Integer targetDeptNo = deptNo;
        for(Dept dept : deptList){
            if(targetDeptNo == dept.getDeptNo()){
                appDeptList.add(DeptDto.builder()
                        .deptNo(dept.getDeptNo())
                        .deptName(dept.getDeptName())
                        .leadEmpNo(dept.getLeadEmpNo())
                        .upperDeptNo(dept.getUpperDeptNo())
                        .build());
                targetDeptNo = dept.getUpperDeptNo();
            }
        }
        return appDeptList;
    }
}
