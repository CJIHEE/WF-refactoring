package workFlow.WFrefactoring.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import workFlow.WFrefactoring.dept.DeptRepository;
import workFlow.WFrefactoring.exception.CheckEmailException;
import workFlow.WFrefactoring.exception.DeptNotFoundException;
import workFlow.WFrefactoring.model.Dept;
import workFlow.WFrefactoring.model.Employee;

import javax.transaction.Transactional;
import javax.validation.Valid;

@Transactional
@Service
@RequiredArgsConstructor
public class EmployeeService {
   private final  EmployeeRepository employeeRepository;

   private  final DeptRepository deptRepository;

    //회원가입
    public EmployeeResponse createEmployee(EmployeeRequset.@Valid CreateEmployee request) {
        //성공 여부
        String successOrFailure = "failure";
        //dept 여부
        Dept dept = deptRepository.findBydeptNo(request.getDeptNo()).orElseThrow(()-> new DeptNotFoundException("dept not found"));
        //존재 유무라서 exists 사용(boolean 타입)
        if(employeeRepository.existsBymail(request.getMail())){
            throw new CheckEmailException("ID duplication");
        }
        //DB에 저장(save),command로받은 데이터 entity에 데이터 주입
        Employee employee = employeeRepository.save(request.toEmployee(dept));
        successOrFailure = "ok";
        //entity 값 VO에 주입
        return EmployeeResponse.toVO(employee, successOrFailure);
    }
}
