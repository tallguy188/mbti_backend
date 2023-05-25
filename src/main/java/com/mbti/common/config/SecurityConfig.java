package com.mbti.common.config;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter{


//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.headers().frameOptions().sameOrigin();
//        http
//                .httpBasic().disable()
//                .csrf().disable()
//                .cors().and()
//                .authorizeRequests()
//                .antMatchers("/register/**").permitAll()
//                .antMatchers("/login/**").permitAll()
//                .antMatchers("/min").permitAll()
//                .antMatchers("/chat").permitAll()
//                .antMatchers("/").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//    }



//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity       // SecurityFilterChain에서 요청에 접근할 수 있어서 인증, 인가 서비스에 사용
//                .httpBasic().disable()  // http basic auth 기반으로 로그인 인증창이 뜬다. 기본 인증을 이용하지 않으려면 .disable()을 추가해준다
//                .csrf().disable()  // csrf, api server이용시 .disable (html tag를 통한 공격)
//                .cors()   // 다른 도메인의 리소스에 대해 접근이 허용되는지 체크
//                .and()  // 묶음 구분(httpBasic(),crsf,cors가 한묶음)
//                .authorizeRequests()  //각 경로 path별 권한 처리
//                .antMatchers("/register/**").permitAll()
//                .antMatchers("/login/**").permitAll()
//                .antMatchers("/min").permitAll()
//                .antMatchers("/createChatRoom").permitAll()
//                .antMatchers("/receive").permitAll()
//                .antMatchers("/send").permitAll()
//                .antMatchers("/chat").permitAll()
//                .antMatchers("/").permitAll()
//                // .antMatchers("/**").permitAll()
//                .and()
//                .sessionManagement()  //세션 관리 기능
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //jwt사용하는 경우 씀(STATELESS는 인증 정보를 서버에 담지 않는다.)
//                //.addFilterBefore(new JwtTokenFilter(userService,secretKey), UsernamePasswordAuthenticationFilter.class)
//                .and()
//                .build();
//    }

//    @Override
//     protected void configure(HttpSecurity http) throws  Exception {
//
//        http.headers().frameOptions().sameOrigin();
//
//    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers("/register/**").permitAll()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true);
    }


}
