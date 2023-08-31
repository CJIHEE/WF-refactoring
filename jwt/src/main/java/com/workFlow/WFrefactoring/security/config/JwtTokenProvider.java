package com.workFlow.WFrefactoring.security.config;

import com.workFlow.WFrefactoring.exception.BlackListToken;
import com.workFlow.WFrefactoring.exception.CheckTokenException;
import com.workFlow.WFrefactoring.security.dto.TokenDto;
import com.workFlow.WFrefactoring.security.service.EmployeeDetailsService;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


import javax.servlet.http.HttpServletRequest;
import javax.swing.text.BadLocationException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private String secretKey;
    private final RedisTemplate<String, String> redisTemplate;
    private final EmployeeDetailsService employeeDetailsService;


    public String resolveToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(StringUtils.hasText(token) && token.startsWith("Bearer")){
            return token.substring(7); // "Bearer "를 뺀 값, 즉 토큰 값
        }
        return null; //throw new IllegalArgumentException("Invalid refresh token");
    }

    public String resolveRefreshToken(HttpServletRequest request){
        String refreshToken = request.getHeader("refreshToken");
        if(StringUtils.hasText(refreshToken) && refreshToken.startsWith("Bearer")){
            return refreshToken.substring(7);
        }
        return null; //throw new IllegalArgumentException("Invalid refresh token");
    }

    //토큰 생성
    public TokenDto generateToken(Authentication authentication) {
        //권한
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        String authorities2 = authentication.getAuthorities().toString();
        String authoritiesName = authentication.getName();

        long timeOffset = 9 * 60 * 60 * 1000; // 9시간을 밀리초로 변환(시차)

        long accessTokenExpirationTime = 1000*60*30; //30분 1000*60*30 (1000*60은 1분)
        long refreshTokenExpirationTime = 7 * 24 * 60 * 60 * 1000; //7일 7 * 24 * 60 * 60 * 1000

        //Access Token
        String accessToken = Jwts.builder()
                .setSubject(authoritiesName)
                .claim("auth", authorities)
                .setIssuedAt(new Date(System.currentTimeMillis()+timeOffset))
                .setExpiration(new Date(System.currentTimeMillis()+accessTokenExpirationTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
        //log.info("날짜 : "+ String.valueOf(new Date(System.currentTimeMillis()+accessTokenExpirationTime)));

        //redis에 accessToken 저장
        redisTemplate.opsForValue().set(
                "ATK"+authoritiesName,
                accessToken,
                accessTokenExpirationTime,
                TimeUnit.MILLISECONDS
        );

        //Refresh Token
        String refreshToken = Jwts.builder()
                .setSubject(authoritiesName)
                .claim("auth", authorities)
                .setExpiration(new Date(System.currentTimeMillis()+refreshTokenExpirationTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        //redis에 refreshToken 저장
        redisTemplate.opsForValue().set(
                "RTK"+authoritiesName,
                refreshToken,
                refreshTokenExpirationTime,
                TimeUnit.MILLISECONDS
        );

        return TokenDto.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    //토큰으로부터 클레임을 만들고, 이를 통해 User 객체를 생성하여 Authentication 객체를 반환
    //JWT 토큰을 복호화하여 토큰에 들어있는 정보를 꺼내는 메서드
    //UsernamePasswordAuthenticationToken으로 보내 인증된 유저인지 확인
    public  Authentication getAuthentication(String accessToken){
        Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(accessToken).getBody();
//        if(claims.get("auth") == null){
//            throw new RuntimeException("권한 정보가 없는 토큰입니다");
//        }
//
//        //권한
//        Collection<? extends GrantedAuthority> authorities =
//                Arrays.stream(claims.get("auth").toString().split(","))
//                        .map(SimpleGrantedAuthority::new)
//                        .collect(Collectors.toList());

        // UserDetails 객체를 만들어서 Authentication 리턴
        //UserDetails principal = new User(claims.getSubject(), "", authorities);
        UserDetails userDetails = employeeDetailsService.loadUserByUsername(claims.getSubject());
        return  new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    //토근 정보를 검증
    public boolean vaildateToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e){
            log.info("invalid JWT Token", e);
        } catch (ExpiredJwtException e){
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e){
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e){
            log.info("JWT claims string is empty", e);
        }
        return false;
    }

    //access토큰 만료시 access,refreshToken 재발급 내려주기
    public static TokenDto setToken(TokenDto tokenDto){
        return tokenDto;
    }

    //JWT 토큰의 만료시간
    public Long getExpiration(String accessToken){
        Date expiration = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(accessToken).getBody().getExpiration();

        long now = new Date().getTime();

        return expiration.getTime() - now;
    }
    public void validateBlackListToken(String accessToken){
        String blackList = redisTemplate.opsForValue().get(accessToken);
        if(StringUtils.hasText(blackList)){
            throw new BlackListToken("로그아웃된 사용자 입니다");
        }
    }
}
