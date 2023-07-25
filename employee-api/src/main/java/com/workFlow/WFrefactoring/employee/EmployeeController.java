package com.workFlow.WFrefactoring.employee;

import com.workFlow.WFrefactoring.employee.dto.EmployeeRequest;
import com.workFlow.WFrefactoring.employee.dto.EmployeeResponse;
import com.workFlow.WFrefactoring.employee.service.EmployeeLoginService;
import com.workFlow.WFrefactoring.employee.service.EmployeeService;
import com.workFlow.WFrefactoring.employee.service.EmployeeSignUpService;
import com.workFlow.WFrefactoring.security.domain.EmployeeDetails;
import com.workFlow.WFrefactoring.security.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("employees")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController{
    private final EmployeeService employeeService;
    private final EmployeeSignUpService employeeSignUpService;
    private final EmployeeLoginService employeeLoginService;
    @PostMapping
    //command 입력받은 데이터
    public EmployeeResponse createEmployee(@RequestBody @Valid EmployeeRequest.CreateEmployee request){
        log.info("CreateEmployee={}", request);
        return employeeSignUpService.SignUpEmployee(request);
    }
    @GetMapping
    public String test(){
       log.info("testSuccess");
       return "success";
    }

    //회원가입
    @PostMapping("/login")
    public TokenDto login(@RequestBody EmployeeRequest.LoginEmployee request){
        log.info("실제3 loginController");
        TokenDto tokenDto = employeeLoginService.login(request);
        return tokenDto;
    }

    //로그아웃
    @DeleteMapping("/logout")
    public String logout(HttpServletRequest request, Principal principal){
        employeeLoginService.logout(request, principal.getName());
        return "success";
    }



}

