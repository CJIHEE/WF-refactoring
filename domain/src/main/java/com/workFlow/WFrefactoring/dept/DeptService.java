package com.workFlow.WFrefactoring.dept;

import com.workFlow.WFrefactoring.exception.DeptNotFoundException;
import com.workFlow.WFrefactoring.model.Dept;
import com.workFlow.WFrefactoring.repository.DeptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class DeptService {

   private  final DeptRepository deptRepository;

    //부서 찾기
    @Transactional(readOnly = true)
    public Integer findBydeptNo(Integer deptNo) {
        //dept 여부
        Dept dept = deptRepository.findAllBydeptNo(deptNo).orElseThrow(()-> new DeptNotFoundException("dept not found"));
        return dept.getDeptNo();
    }
}
