package com.workFlow.WFrefactoring.employee;

import com.workFlow.WFrefactoring.dto.EmployeeRequest;
import com.workFlow.WFrefactoring.employee.dto.EmployeeServiceDto;
import com.workFlow.WFrefactoring.employee.dto.EmployeeResponse;

import com.workFlow.WFrefactoring.employee.service.EmployeeService;
import com.workFlow.WFrefactoring.security.domain.EmployeeDetails;
import com.workFlow.WFrefactoring.security.dto.TokenDto;
import com.workFlow.WFrefactoring.service.EmployeeLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("employees")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController{
    private final EmployeeService employeeService;
    private final EmployeeLoginService employeeLoginService;

    //회원가입
    @PostMapping
    public EmployeeResponse createEmployee(@RequestBody @Valid EmployeeRequest.CreateEmployee request){
        return employeeService.createEmployee(request.convertToServiceDTO());
    }

    //로그인
    @PostMapping("/login")
    public TokenDto login(@RequestBody EmployeeRequest.LoginEmployee request){
        TokenDto tokenDto = employeeLoginService.login(request.convertToServiceDTO());
        return tokenDto;
    }

    //로그아웃
    @DeleteMapping("/logout")
    public String logout(HttpServletRequest request, Principal principal){
        employeeLoginService.logout(request, principal.getName());
        return "success";
    }

    //전체 회원 조회
    @GetMapping
    private List<EmployeeResponse> getAllEmployee(Long lastEmpNo, Integer pageSize){
        return employeeService.getAllEmployee(lastEmpNo, pageSize);
    }

    //특정 사원 조회 (empNo로 조회)
    @GetMapping("/{empNo}")
    private EmployeeResponse getEmployee(@PathVariable("empNo") Long empNo){
        return employeeService.getEmployee(empNo);
    };

    //회원 정보 업데이트
    @PutMapping("/{empNo}")
    private EmployeeResponse updateEmployee(@PathVariable("empNo") Long empNo,
                                            @RequestBody @Valid EmployeeRequest.UpdateEmployee request,
                                            @AuthenticationPrincipal EmployeeDetails employeeDetails){
        return employeeService.updateEmployee(empNo,request.convertToServiceDto(),employeeDetails);
    }

}

