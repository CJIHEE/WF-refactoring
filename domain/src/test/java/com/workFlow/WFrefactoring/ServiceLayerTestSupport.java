package com.workFlow.WFrefactoring;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public abstract class ServiceLayerTestSupport {
    @MockBean
    protected PasswordEncoder passwordEncoder;
}
