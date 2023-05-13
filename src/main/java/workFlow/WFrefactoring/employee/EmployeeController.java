package workFlow.WFrefactoring.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping
    //command 입력받은 데이터
    public EmployeeResponse createEmployee(@RequestBody @Valid EmployeeRequset.CreateEmployee request){
        return employeeService.createEmployee(request);
    }

}

