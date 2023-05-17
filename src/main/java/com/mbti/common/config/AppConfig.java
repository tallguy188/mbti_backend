package com.mbti.common.config;


import com.mbti.common.interceptor.TokenInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan("com.mbti.common.util")
@AllArgsConstructor
public class AppConfig implements WebMvcConfigurer {
    private final TokenInterceptor tokenInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 인터셉터를 등록하고 경로 패턴을 설정
        registry.addInterceptor(tokenInterceptor)
                .excludePathPatterns("/")
                .excludePathPatterns("/login")
                .excludePathPatterns("/register");


    }

}
