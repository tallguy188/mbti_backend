package com.mbti.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JwtTokenUtil {   // 토큰 생성 공간

    public static String createToken(String username, long expireTimeMs, String key) {
        Claims claims = Jwts.claims(); // 일종의map
        claims.put("username", username);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTimeMs))
                .signWith(SignatureAlgorithm.HS256,key)  //개인키를 넘겨받아 해당키로 토큰 암호화
                .compact()
                ;

    }
    // 토큰 검증 메소드
    public static Claims validateToken(String token, String key) throws JwtException {
        try {
            // 파싱된 토큰에서 클레임 추출
            return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
            // 토큰이 만료되었거나 유효하지 않으면 예외처리
        }catch(JwtException | IllegalArgumentException e){   // JwtException 혹은 IllegalArgumentException이 발생했을 떄
            throw new JwtException("만료되거나 유요하지 않은 토큰입니다.");
        }
    }

}
