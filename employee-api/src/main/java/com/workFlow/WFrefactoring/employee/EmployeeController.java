package com.workFlow.WFrefactoring.employee;

import com.workFlow.WFrefactoring.employee.dto.EmployeeRequest;
import com.workFlow.WFrefactoring.employee.dto.EmployeeResponse;

import com.workFlow.WFrefactoring.employee.service.EmployeeService;
import com.workFlow.WFrefactoring.security.domain.EmployeeDetails;
import com.workFlow.WFrefactoring.security.dto.TokenDto;
import com.workFlow.WFrefactoring.service.EmployeeLoginService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

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
    @ApiOperation(value="사원 등록", notes ="신규 사원 등록" , response = EmployeeRequest.CreateEmployee.class)
    @PostMapping
    public EmployeeResponse createEmployee(@RequestBody @Valid EmployeeRequest.CreateEmployee request){
        return employeeService.createEmployee(request.convertToServiceDTO());
    }

    //로그인
    @ApiOperation(value="로그인", notes ="로그인")
    @PostMapping("/login")
    public TokenDto login(@RequestBody EmployeeRequest.LoginEmployee request){
        TokenDto tokenDto = employeeLoginService.login(request.convertToServiceDTO());
        return tokenDto;
    }

    //로그아웃
    @ApiOperation(value="로그아웃", notes ="로그 아웃")
    @DeleteMapping("/logout")
    public String logout(HttpServletRequest request, @ApiIgnore Principal principal){
        employeeLoginService.logout(request, principal.getName());
        return "success";
    }

    //전체 회원 조회
    @ApiOperation(value="사원 목록", notes ="전체 사원 정보 목록")
    @ApiImplicitParams({@ApiImplicitParam(name = "lastEmpNo", value = "마지막 조회 사원 번호", required = false, dataType = "Long", example = "0" ,paramType = "query")
                    , @ApiImplicitParam(name = "pageSize", value = "조회 사원 수", required = false, dataType = "Integer", paramType = "query", example = "3", defaultValue = "3")})
    @GetMapping
    private List<EmployeeResponse> getAllEmployee(@RequestParam(name = "lastEmpNo", required = false) Long lastEmpNo, @RequestParam(name = "pageSize", required = false)  Integer pageSize){
        return employeeService.getAllEmployee(lastEmpNo, pageSize);
    }

    //특정 사원 조회 (empNo로 조회)
    @ApiOperation(value="사원 정보", notes ="특정 사원 한명에 대한 정보")
    @ApiImplicitParam(name = "empNo", value = "사원 아이디", required = true, dataType = "Long", example = "0",paramType = "path")
    @GetMapping("/{empNo}")
    private EmployeeResponse getEmployee(@PathVariable("empNo") Long empNo){
        return employeeService.getEmployee(empNo);
    };

    //회원 정보 업데이트
    @ApiOperation(value="사원 정보 수정", notes ="특정 사원 정보를 수정")
    @ApiImplicitParam(name = "empNo", value = "사원 아이디", required = true, dataType = "Long", example = "0", paramType = "path")
    @PutMapping("/{empNo}")
    private EmployeeResponse updateEmployee(@PathVariable("empNo") Long empNo,
                                            @RequestBody @Valid EmployeeRequest.UpdateEmployee request,
                                            @AuthenticationPrincipal @ApiIgnore EmployeeDetails employeeDetails){
        return employeeService.updateEmployee(empNo,request.convertToServiceDto(),employeeDetails);
    }


}

