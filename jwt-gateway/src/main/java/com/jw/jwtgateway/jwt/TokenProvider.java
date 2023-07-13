package com.jw.jwtgateway.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;


/**
 * JWT TOKEN PROVIDER
 * 설명
 *    신규 토큰 생성, 토큰 유효성 검사, 토큰 권한 확인 등을 진행
 * Dependency
 *    implementation 'io.jsonwebtoken:jjwt-api:0.11.5'
 *    implementation 'io.jsonwebtoken:jjwt-jackson:0.11.5'
 *    runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.11.5'
 * 환경
 *    Java 11
 *    Spring boot 2.7
 * */
@Component

public class TokenProvider implements InitializingBean {

    private final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

    private static final String AUTHORITIES_KEY = "auth";

    private final String secret = "c2lsdmVybmluZS10ZWNoLXNwcmluZy1ib290LWp3dC10dXRvcmlhbC1zZWNyZXQtc2lsdmVybmluZS10ZWNoLXNwcmluZy1ib290LWp3dC10dXRvcmlhbC1zZWNyZXQK";
    private final long tokenValidityInMilliseconds = 86400;

    private Key key;

    @Override
    public void afterPropertiesSet() throws Exception {
        //Base64인코딩 String key값을 Base64 디코딩 후 byte[] 변환
        byte[] keyByte = Decoders.BASE64.decode(secret);
        //앞전에 생성된 byte 배열 key값을 사용하여 Key객체 초기화
        this.key = Keys.hmacShaKeyFor(keyByte);
    }

    public String createToken(Authentication authentication){

        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        logger.info("[TokenProvider::createToken 01] Authentication Name: {}", authentication.getName());
        logger.info("[TokenProvider::createToken 02] Authentication: {}", authorities);

        long now = new Date().getTime();
        Date validity = new Date(now + this.tokenValidityInMilliseconds);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
            /*
                JwtBuilder는 JWT스펙에 정의한 기본으로 등록된 Cliam names에 대해서 다음과 같은 편리한 setter 메서드를 제공한다.
                setIssuer: iss (Issuer) Claim
                setSubject: sub (Subject) Claim
                setAudience: aud (Audience) Claim
                setExpiration: exp (Expiration Time) Claim
                setNotBefore: nbf (Not Before) Claim
                setIssuedAt: iat (Issued At) Claim
                setId: jit(JWT ID) Claim
            */
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();

    }

    public Authentication getAuthentication(String token){
        Claims claims = Jwts
                            .parserBuilder()
                            .setSigningKey(key)
                            .build()
                            .parseClaimsJws(token)
                        .getBody();

        logger.info("[TokenProvider:getAuthentication 01] Claims: {}", claims.toString());

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);

        Authentication result = new UsernamePasswordAuthenticationToken(principal, token, authorities);

        logger.info("[TokenProvider:getAuthentication 02] Authentication: {}", result.toString());

        return result;
    }

    public boolean validateToken(String token){

        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }catch (SecurityException | MalformedJwtException e){
            logger.error("잘못된 JWT 서명입니다.", e);
        }catch (ExpiredJwtException e){
            logger.error("만료된 JWT 토큰입니다.", e);
        }catch (UnsupportedJwtException e){
            logger.error("지원하지 않는 형식의 JWT 토큰입니다.", e);
        }catch (IllegalArgumentException e){
            logger.error("JWT 토큰 정보가 잘못되었습니다.", e);
        }

        return false;
    }
}
