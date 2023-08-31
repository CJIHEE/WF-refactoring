package com.workFlow.WFrefactoring.employee.service;

import com.workFlow.WFrefactoring.repository.RepositoryCustom.EmployeeRepositoryCustom;
import com.workFlow.WFrefactoring.dept.dto.DeptDto;
import com.workFlow.WFrefactoring.dept.service.DeptService;
import com.workFlow.WFrefactoring.employee.dto.EmployeeRequest;
import com.workFlow.WFrefactoring.employee.dto.EmployeeResponse;
import com.workFlow.WFrefactoring.exception.CheckEmailException;
import com.workFlow.WFrefactoring.exception.UserNotFoundException;
import com.workFlow.WFrefactoring.exception.checkAutenticationException;
import com.workFlow.WFrefactoring.model.Employee;
import com.workFlow.WFrefactoring.repository.EmployeeRepository;
import com.workFlow.WFrefactoring.security.domain.EmployeeDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final DeptService deptService;

    //회원가입
    @Transactional
    public EmployeeResponse createEmployee(@Valid EmployeeRequest.CreateEmployee request) {
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

        List<Employee> empList = employeeRepository.findEmpAll(lastEmpNo, pageSize);
        List<EmployeeResponse> response = new ArrayList<>();

        if(!empList.isEmpty()){
            for(Employee employee : empList){
                response.add(EmployeeResponse.toVO(employee));
            }
        }
        return response;
    }

    //특정 사원 조회
    @Transactional
    public EmployeeResponse getEmployee(Long empNo) {
        Employee employee = employeeRepository.findById(empNo).orElseThrow(()->new UserNotFoundException("user not found"));
        return EmployeeResponse.toVO(employee);
    }

    //회원 정보 업데이트
    @Transactional
    public EmployeeResponse updateEmployee(Long empNo, EmployeeRequest.UpdateEmployee request, EmployeeDetails employeeDetails) {

        Employee employee = employeeRepository.findById(empNo).orElseThrow(()->new UsernameNotFoundException("user not found"));
        //로그인한 본인의 정보만 변경 가능
        if(employee.getMail().equals(employeeDetails.getUsername())){
            //dept 여부
            deptService.findBydeptNo(request.getDeptNo());

            employee.updateEmployee(request.getDeptNo(),
                    request.getAddr(), request.getPosition(),
                    request.getPhone(), request.getRetirementDate(),
                    request.getEmpStatus());

            return EmployeeResponse.toVO(employee);
        }
        else{
            throw new checkAutenticationException("user not match");
        }

    }
}