package com.workFlow.WFrefactoring.security.config;

import com.workFlow.WFrefactoring.security.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("실제1 doFilterInternal");
        //1.Request Header 에서 JWT 토큰 추출
        String accessToken = resolveToken(request);
        //refreash토큰 추출
        String refreshToken = resolveRefreshToken(request);
        log.info("엑세스토큰: " + accessToken);
        log.info("리프레쉬토큰:" + refreshToken);

        //2.validateToken으로 토큰 유효성 검사(토큰 있다면실행 / login요청 페이지에서는 적용X)
        if(StringUtils.hasText(accessToken) && jwtTokenProvider.vaildateToken(accessToken)){
            // 토큰이 유효할 경우 토큰에서 Authentication 객체를 가지고 와서 SecurityContext 에 저장
            Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("예상7 doFilterInternal");
            log.info("jwtProvider authentication={}",String.valueOf(authentication));
        }
        //accessToken 만료시 refreshToken 검증
        else if(!jwtTokenProvider.vaildateToken(accessToken) && StringUtils.hasText(refreshToken) && jwtTokenProvider.vaildateToken(refreshToken)){
            //검증 통과시 refreshToken에서 권한정보 가져오기
            Authentication authentication = jwtTokenProvider.getAuthentication(refreshToken);
            //accessToken,refreshToken 모두 재발급
            TokenDto tokenDto = jwtTokenProvider.generateToken(authentication);
            //토큰 내려주기
            JwtTokenProvider.setToken(tokenDto);
            log.info("재발급");
            //SecurityContext 에 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        log.info("실제2 request={}",request);
        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        log.info("실제2 doFilterInternal");
        String token = request.getHeader("Authorization");
        if(StringUtils.hasText(token) && token.startsWith("Bearer")){
            return token.substring(7); // "Bearer "를 뺀 값, 즉 토큰 값
        }
        throw new IllegalArgumentException("Invalid refresh token");
    }

    private String resolveRefreshToken(HttpServletRequest request){
        String refreshToken = request.getHeader("refreshToken");
        if(StringUtils.hasText(refreshToken) && refreshToken.startsWith("Bearer")){
                return refreshToken.substring(7);
        }
        throw new IllegalArgumentException("Invalid refresh token");
    }
}


