package workFlow.WFrefactoring.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import workFlow.WFrefactoring.dept.DeptRepository;
import workFlow.WFrefactoring.dept.DeptService;
import workFlow.WFrefactoring.exception.CheckEmailException;
import workFlow.WFrefactoring.exception.DeptNotFoundException;
import workFlow.WFrefactoring.model.Dept;
import workFlow.WFrefactoring.model.Employee;

import javax.transaction.Transactional;
import javax.validation.Valid;

@Service
@RequiredArgsConstructor
public class EmployeeService {
   private final  EmployeeRepository employeeRepository;
   private final DeptService deptService;


    //회원가입
    @Transactional
    public EmployeeResponse createEmployee(EmployeeRequset.@Valid CreateEmployee request) {
        //dept 여부
        Integer dept = deptService.findBydeptNo(request.getDeptNo());
        //존재 유무라서 exists 사용(boolean 타입)
        if(employeeRepository.existsBymail(request.getMail())){
            throw new CheckEmailException("ID duplication");
        }
        //DB에 저장(save),command로받은 데이터 entity에 데이터 주입
        Employee employee = employeeRepository.save(request.toEmployee(dept));
        //entity 값 VO에 주입
        return EmployeeResponse.toVO(employee);
    }
}
