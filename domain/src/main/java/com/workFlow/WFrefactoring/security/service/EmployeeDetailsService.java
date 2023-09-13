package com.workFlow.WFrefactoring.security.service;

import com.workFlow.WFrefactoring.model.Employee;
import com.workFlow.WFrefactoring.repository.EmployeeRepository;
import com.workFlow.WFrefactoring.security.domain.EmployeeDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeDetailsService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;
    @Override
    public UserDetails loadUserByUsername(String mail){
        Employee employee = employeeRepository.findBymail(mail);
        if(employee == null){
            throw new UsernameNotFoundException("user not found");
        }
        return new EmployeeDetails(employee);
    }

}
