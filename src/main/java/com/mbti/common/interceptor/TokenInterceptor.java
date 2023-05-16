package com.mbti.common.interceptor;


import com.mbti.common.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class TokenInterceptor implements HandlerInterceptor {

    private String secretKey;

    public TokenInterceptor(Environment environment) {
        this.secretKey = environment.getProperty("jwt.secret");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler)throws Exception {
        // 헤더에서 토큰 추출
        String token = request.getHeader("Authorization");

        // 토큰 유효성 검사
        if (token == null || !token.startsWith("Bearer")) {

            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
        try {
            // 토큰 검증
            String accessToken = token.substring(7); //bearer이후의 토큰 검증
            Claims claims = JwtTokenUtil.validateToken(accessToken,secretKey);

            // 토큰에서 필요한 정보 추출 및 처리
            String username = claims.get("username",String.class);
            return true;
        }catch (Exception e) {

            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;

        }
    }

}
