package com.mbti.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

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
}
