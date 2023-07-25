package com.workFlow.WFrefactoring.security.config;

import com.workFlow.WFrefactoring.exception.CheckTokenException;
import com.workFlow.WFrefactoring.security.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
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
    private final RedisTemplate<String, String> redisTemplate;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("실제1 doFilterInternal");
        //1.Request Header 에서 JWT 토큰 추출
        String accessToken = jwtTokenProvider.resolveToken(request);
        //refreash토큰 추출
        String refreshToken = jwtTokenProvider.resolveRefreshToken(request);

        log.info("엑세스토큰: " + accessToken);
        log.info("리프레쉬토큰:" + refreshToken);

        //로그아웃 된 토큰인지 확인
        jwtTokenProvider.validateBlackListToken(accessToken);

        //2.validateToken으로 토큰 유효성 검사(토큰 있다면실행 / login요청 페이지에서는 적용X) (redis 검증도 추가해서 가장 최신 accessToken만 사용가능)
        if(StringUtils.hasText(accessToken) && jwtTokenProvider.vaildateToken(accessToken) ){
            // 토큰이 유효할 경우 토큰에서 Authentication 객체를 가지고 와서 SecurityContext 에 저장
            Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
            // 가장 최근 토큰인지 확인
            String ATK = (String)redisTemplate.opsForValue().get("ATK"+authentication.getName());
            if(!ATK.equals(accessToken)){
                throw new CheckTokenException("최신 토큰을 사용하세요");
            }
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("예상7 doFilterInternal");
            log.info("jwtProvider authentication={}",String.valueOf(authentication));
        }
        //accessToken 만료시 refreshToken 검증 (redis 검증도 추가해서 가장 최신 refreshToken만 사용가능)
        else if(!jwtTokenProvider.vaildateToken(accessToken) && jwtTokenProvider.vaildateToken(refreshToken)){
            //검증 통과시 refreshToken에서 권한정보 가져오기
            Authentication authentication = jwtTokenProvider.getAuthentication(refreshToken);
            // 가장 최근 토큰인지 확인
            String RTK = (String)redisTemplate.opsForValue().get("RTK"+authentication.getName());
            if(!RTK.equals(refreshToken)){
                throw new CheckTokenException("최신 refresh 토큰을 사용하세요");
            }
            //accessToken,refreshToken 모두 재발급(refreshTokeb 발급마다 redis에 저장해줘서 따로 update 로직 필요X)
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

//    private String resolveToken(HttpServletRequest request) {
//        log.info("실제2 doFilterInternal");
//        String token = request.getHeader("Authorization");
//        if(StringUtils.hasText(token) && token.startsWith("Bearer")){
//            return token.substring(7); // "Bearer "를 뺀 값, 즉 토큰 값
//        }
//        return null; //throw new IllegalArgumentException("Invalid refresh token");
//    }
//
//    private String resolveRefreshToken(HttpServletRequest request){
//        String refreshToken = request.getHeader("refreshToken");
//        if(StringUtils.hasText(refreshToken) && refreshToken.startsWith("Bearer")){
//                return refreshToken.substring(7);
//        }
//        return null; //throw new IllegalArgumentException("Invalid refresh token");
//    }

}


