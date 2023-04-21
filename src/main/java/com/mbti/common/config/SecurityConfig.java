package com.mbti.common.config;

import com.mbti.application.UserService;
//import com.mbti.jwt.JwtAuthenticationFilter;
//import com.mbti.jwt.JwtTokenProvider;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration

public class SecurityConfig{


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity       // SecurityFilterChain에서 요청에 접근할 수 있어서 인증, 인가 서비스에 사용
                .httpBasic().disable()  // http basic auth 기반으로 로그인 인증창이 뜬다. 기본 인증을 이용하지 않으려면 .disable()을 추가해준다
                .csrf().disable()  // csrf, api server이용시 .disable (html tag를 통한 공격)
                .cors()   // 다른 도메인의 리소스에 대해 접근이 허용되는지 체크
                .and()  // 묶음 구분(httpBasic(),crsf,cors가 한묶음)
                .authorizeRequests()  //각 경로 path별 권한 처리
                .antMatchers("/join/**").permitAll()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/**").permitAll()
                .and()
                .sessionManagement()  //세션 관리 기능
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //jwt사용하는 경우 씀(STATELESS는 인증 정보를 서버에 담지 않는다.)
                //.addFilterBefore(new JwtTokenFilter(userService,secretKey), UsernamePasswordAuthenticationFilter.class)
                .and()
                .build();
    }


}
