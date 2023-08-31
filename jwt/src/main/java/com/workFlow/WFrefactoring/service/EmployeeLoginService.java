package com.workFlow.WFrefactoring.service;

import com.workFlow.WFrefactoring.employee.dto.EmployeeRequest;
import com.workFlow.WFrefactoring.security.config.JwtTokenProvider;
import com.workFlow.WFrefactoring.security.dto.TokenDto;
import com.workFlow.WFrefactoring.security.service.EmployeeDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeLoginService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final EmployeeDetailsService employeeDetailsService;

    private final RedisTemplate<String, String> redisTemplate;

    @Transactional(readOnly=true)
    public TokenDto login(EmployeeRequest.LoginEmployee request){
        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getMail(), request.getPw());
        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 EmployeeDetailService에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = jwtTokenProvider.generateToken(authentication);
        return tokenDto;
    }

    public void logout(HttpServletRequest request, String username) {
        //SecurityContextHolder.clearContext();
        //accessToken 추출
        String accessToken = jwtTokenProvider.resolveToken(request);

        //엑세스 토큰 남은 유효기간
        Long expiration = jwtTokenProvider.getExpiration(accessToken);

        //Redis BlackList 저장
        redisTemplate.opsForValue().set(
                accessToken,
                "logout",
                expiration,
                TimeUnit.MILLISECONDS
        );

        //리프레쉬 토큰 삭제
        redisTemplate.delete("RTK"+username);
    }
}
