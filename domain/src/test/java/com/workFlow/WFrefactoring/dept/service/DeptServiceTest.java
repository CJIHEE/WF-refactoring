package com.workFlow.WFrefactoring.dept.service;

import com.workFlow.WFrefactoring.ServiceLayerTestSupport;
import com.workFlow.WFrefactoring.dept.dto.DeptDto;
import com.workFlow.WFrefactoring.exception.DeptNotFoundException;
import com.workFlow.WFrefactoring.model.Dept;
import com.workFlow.WFrefactoring.repository.DeptRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class DeptServiceTest extends ServiceLayerTestSupport {
    @Autowired
    private DeptRepository deptRepository;

    @Autowired
    private DeptService deptService;

    @Autowired
    private EntityManager entityManager;

    @AfterEach
    public void tearDown(){deptRepository.deleteAllInBatch();}


    @DisplayName("부서번호를 받아서 해당 부서의 정보를 조회할 수 있다.")
    @Test
    public void findByDeptNo_success(){
        // given
        Dept dept = Dept.builder()
                .deptNo(100)
                .deptName("test")
                .leadEmpNo(1L)
                .upperDeptNo(10)
                .build();

        Dept savedDept = deptRepository.save(dept);
        Integer deptNo = savedDept.getDeptNo();

        // when
        DeptDto result = deptService.findBydeptNo(deptNo);

        // then
        assertThat(result.getDeptNo()).isEqualTo(savedDept.getDeptNo());
        assertThat(result.getDeptName()).isEqualTo(savedDept.getDeptName());
        assertThat(result.getLeadEmpNo()).isEqualTo(savedDept.getLeadEmpNo());
        assertThat(result.getUpperDeptNo()).isEqualTo(savedDept.getUpperDeptNo());
    }

    @DisplayName("부서가 존재하지 않는다면 DeptNotFoundException이 발생한다.")
    @Test
    public void findByDeptNo_fail1(){
        // given
        Integer deptNo = 100;

        // when & then
        assertThatThrownBy(() -> deptService.findBydeptNo(deptNo))
                .isInstanceOf(DeptNotFoundException.class)
                .hasMessage("dept not found");
    }

}