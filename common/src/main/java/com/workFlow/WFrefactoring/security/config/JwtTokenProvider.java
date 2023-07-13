package com.workFlow.WFrefactoring.security.config;

import com.workFlow.WFrefactoring.security.dto.TokenDto;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private String secretKey;

    //토큰 생성
    public TokenDto generateToken(Authentication authentication) {
        log.info("실제8 <jwtTokenProvider>");
        //권한
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        String authorities2 = authentication.getAuthorities().toString();
        log.info("아마 authorities2는 캐시 되는듯?");
        String authoritiesName = authentication.getName();

        log.info("실제 10 <jwtTokenProvider>");
        log.info("authoritiesToString={}", authorities2);
        log.info("authoritiesName={}", authoritiesName);

        long timeOffset = 9 * 60 * 60 * 1000; // 9시간을 밀리초로 변환(시차)

        //Access Token
        String accessToken = Jwts.builder()
                .setSubject(authoritiesName)
                .claim("auth", authorities)
                .setIssuedAt(new Date(System.currentTimeMillis()+timeOffset))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*30+timeOffset))//30분
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        //Refresh Token
        String refreshToken = Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis()+7 * 24 * 60 * 60 * 1000+timeOffset))//7일
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

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
        log.info("예상9 jwtTokenProvider/getAuthentication");
        Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(accessToken).getBody();
        if(claims.get("auth") == null){
            throw new RuntimeException("권한 정보가 없는 토큰입니다");
        }

        //권한
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("auth").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        // UserDetails 객체를 만들어서 Authentication 리턴
        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return  new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    //토근 정보를 검증
    public boolean vaildateToken(String token){
        log.info("언제나올까");
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

}
