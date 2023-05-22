package com.mbti.common.config;


import com.mbti.common.interceptor.TokenInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
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
                .excludePathPatterns("/register")
                .excludePathPatterns("/min")
                .excludePathPatterns("/swagger-ui/**"); // Swagger UI 경로 제외
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET","POST","PUT","DELETE")
                .allowedHeaders("Authorization")  //Authorization
                .allowCredentials(true);
    }
}
