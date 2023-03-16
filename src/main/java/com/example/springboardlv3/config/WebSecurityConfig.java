package com.example.springboardlv3.config;

import com.example.springboardlv3.jwt.JwtAuthFilter;
import com.example.springboardlv3.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
@EnableGlobalMethodSecurity(securedEnabled = true) // @Secured 어노테이션 활성화
public class WebSecurityConfig {

    private final JwtUtil jwtUtil;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() { // 시큐리티를 쓰면 url
        // h2-console 사용 및 resources 접근 허용 설정
        return (web) -> web.ignoring()
                .requestMatchers(PathRequest.toH2Console())
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        // 기본 설정인 Session 방식은 사용하지 않고 JWT 방식을 사용하기 위한 설정
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests().antMatchers("/api/user/login").permitAll() // permitAll() : 모든사람이 사용가능하게 만들어준다. 관리자만, 회원만 접근 가능하게도 만들수 있다. 제일위에는 무엇?
                .antMatchers(HttpMethod.GET,"/api/posts").permitAll() // 모든 유저는 게시글 조회가 가능하게 해야한다.
                .antMatchers(HttpMethod.GET,"/api/post/{id}").permitAll() // 세부적으로 나눠서 모든 유저를 접근 가능하게 만든다. 상세조회 {id}가 맞는지
                .antMatchers(HttpMethod.POST,"/api/user/signup").permitAll() // 회원가입
//                .antMatchers(HttpMethod.POST,"/api/user/login").permitAll() // 로그인
                .anyRequest().authenticated()
                // JWT 인증/인가를 사용하기 위한 설정
                .and().addFilterBefore(new JwtAuthFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

//        http.formLogin().loginPage("/api/user/login-page").permitAll(); // 권한이 없는 사람들은 여기로 보낸다.

        http.exceptionHandling().accessDeniedPage("/api/user/forbidden");

        return http.build();
    }

}