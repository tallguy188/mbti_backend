package com.mbti.common.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class EncrypterConfig {

    @Bean
    public BCryptPasswordEncoder encodePwd() {   // password를 인코딩 해줄때 쓰기 위함
        return new BCryptPasswordEncoder();
    }
}
