package com.workFlow.WFrefactoring.security.domain;

import com.workFlow.WFrefactoring.model.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor
@Slf4j
public class EmployeeDetails implements UserDetails {
    private final Employee employee;
    private static final String ROLE_PREFIX = "ROLE_";
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + employee.getRole()));
        log.info("role={}", authorities);
        return authorities;
    }

    @Override
    public String getPassword() {
        return employee.getPw();
    }

    @Override
    public String getUsername() {
        return employee.getMail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
