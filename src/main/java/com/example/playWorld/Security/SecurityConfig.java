package com.example.playWorld.Security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final String[] allowedUrls = {"/", "/home"};
    @Bean
    PasswordEncoder passwordEncoder() {
        // TODO 나중에 모듈 변경하기 -> BCryptPasswordEncoder()
        return new SimplePasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions(frameOption -> frameOption.sameOrigin())) // h2 사용을 위한 설정
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(allowedUrls).permitAll() // "/", "/home" 요청은 허용
                        .requestMatchers(PathRequest.toH2Console()).permitAll()
                        .anyRequest().authenticated() // 그 외 요청은 인증 필요
                )
                .formLogin((form) -> form
                        .loginPage("/login") // 로그인 페이지 설정
                        .permitAll() // 로그인 페이지는 모두에게 허용
                )
                .logout((logout) -> logout.permitAll())
        ;

        return http.build();

    }


}
