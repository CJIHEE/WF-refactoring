package com.workFlow.WFrefactoring.employee.service;

import java.util.*;
import com.workFlow.WFrefactoring.dept.dto.DeptDto;
import com.workFlow.WFrefactoring.employee.dto.EmployeeRequest;
import com.workFlow.WFrefactoring.employee.dto.EmployeeResponse;
import com.workFlow.WFrefactoring.exception.CheckEmailException;
import com.workFlow.WFrefactoring.exception.UserNotFoundException;
import com.workFlow.WFrefactoring.model.Employee;
import com.workFlow.WFrefactoring.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    //회원가입
    @Transactional
    public EmployeeResponse createEmployee(EmployeeRequest.CreateEmployee request, DeptDto deptDto) {
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
    public List<Object> getAllEmployee(Pageable pageable) {

        Page<Employee> page = employeeRepository.findAll(pageable);
        List<Employee> employees = page.getContent();
        List<Object> response = new ArrayList<>();

        if(!employees.isEmpty()){

            Map<String, Integer> totalMap = new HashMap<>();
            totalMap.put("total",page.getTotalPages());

            response.add(totalMap);

            for(Employee employee : employees){
                response.add(EmployeeResponse.toVO(employee));
            }
        }
        else{
            response.add("조회된 회원이 없습니다");
        }

        return response;
    }

    //특정 사원 조회
    public EmployeeResponse getEmployee(Long empNo) {
        Employee employee = employeeRepository.findById(empNo).orElseThrow(()->new UserNotFoundException("user not found"));
        return EmployeeResponse.toVO(employee);
    }


}