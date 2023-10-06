package com.workFlow.WFrefactoring.employee.service;

import com.workFlow.WFrefactoring.dept.dto.DeptDto;
import com.workFlow.WFrefactoring.dept.service.DeptService;
import com.workFlow.WFrefactoring.employee.dto.EmployeeServiceDto;
import com.workFlow.WFrefactoring.employee.dto.EmployeeResponse;
import com.workFlow.WFrefactoring.exception.CheckEmailException;
import com.workFlow.WFrefactoring.exception.UserNotFoundException;
import com.workFlow.WFrefactoring.exception.CheckAutenticationException;
import com.workFlow.WFrefactoring.model.Employee;
import com.workFlow.WFrefactoring.repository.EmployeeRepository;
import com.workFlow.WFrefactoring.security.domain.EmployeeDetails;
import com.workFlow.WFrefactoring.vo.EmployeeVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final DeptService deptService;

    //회원가입
    @Transactional
    public EmployeeResponse createEmployee(@Valid EmployeeServiceDto.CreateEmployee request) {
        //dept 여부
        DeptDto deptDto = deptService.findBydeptNo(request.getDeptNo());
        //ID존재 유무, 존재 유무라서 exists 사용(boolean 타입)
        if(employeeRepository.existsBymail(request.getMail())){
            throw new CheckEmailException("ID duplication");
        }
        //암호화
        String passWord = passwordEncoder.encode(request.getPw());
        //DB에 저장(save),command로받은 데이터 entity에 데이터 주입(DTO->entity)
        Employee employee = employeeRepository.save(request.toEmployee(deptDto.getDeptNo(),passWord));
        //entity 값 VO에 주입
        return EmployeeResponse.toVO(employee);
    }

    //전체 회원 조회
    @Transactional
    public List<EmployeeResponse> getAllEmployee(Long lastEmpNo, Integer pageSize) {
        if(pageSize == null) pageSize = 5;
        if(lastEmpNo == null) lastEmpNo = 1L;

        List<Employee> empList = employeeRepository.findEmpAll(lastEmpNo, pageSize);

        return empList.stream() //스트림생성
                .map(EmployeeResponse::toVO) //가공
                .collect(Collectors.toList());
    }

    //특정 사원 조회
    @Transactional
    public EmployeeResponse getEmployee(Long empNo) {
        Employee employee = employeeRepository.findById(empNo).orElseThrow(()->new UserNotFoundException("user not found"));
        return EmployeeResponse.toVO(employee);
    }

    //회원 정보 업데이트
    @Transactional
    public EmployeeResponse updateEmployee(Long empNo, EmployeeServiceDto.UpdateEmployee request, EmployeeDetails employeeDetails) {

        Employee employee = employeeRepository.findById(empNo).orElseThrow(()->new UsernameNotFoundException("user not found"));
        //로그인한 본인의 정보만 변경 가능
        if(!employee.getMail().equals(employeeDetails.getUsername())){
            throw new CheckAutenticationException("user not match");
        }

       EmployeeVO employeeVO = EmployeeVO.toEmployeeVO(request.getAddr(), request.getPosition(),
                request.getPhone(), request.getEmpStatus());

        employee.updateEmployee(employeeVO);

        return EmployeeResponse.toVO(employee);

    }
}