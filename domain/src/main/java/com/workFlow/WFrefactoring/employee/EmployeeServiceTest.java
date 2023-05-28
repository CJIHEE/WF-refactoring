package com.workFlow.WFrefactoring.employee;

import com.workFlow.WFrefactoring.model.Employee;
import com.workFlow.WFrefactoring.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceTest {
   private final EmployeeRepository employeeRepository;

    //테스트 코드 reset
    public void clear() {
        employeeRepository.deleteAllInBatch();
    }
    //테스트 코드
    public String findBymail(String mail) {
        Employee findEmployee = employeeRepository.findBymail(mail);
        return findEmployee.getMail();
    }
}
