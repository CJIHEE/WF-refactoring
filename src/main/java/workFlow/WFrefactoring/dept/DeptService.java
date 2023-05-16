package workFlow.WFrefactoring.dept;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import workFlow.WFrefactoring.exception.DeptNotFoundException;
import workFlow.WFrefactoring.model.Dept;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;


@Service
@RequiredArgsConstructor
public class DeptService {

   private  final DeptRepository deptRepository;

    //부서 찾기
    @Transactional
    public Integer findBydeptNo(Integer deptNo) {
        //dept 여부
        Dept dept = deptRepository.findBydeptNo(deptNo).orElseThrow(()-> new DeptNotFoundException("dept not found"));
        return dept.getDeptNo();
    }
}
