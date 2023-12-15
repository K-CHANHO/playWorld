package com.example.playWorld.security;

import com.example.playWorld.token.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final String[] allowedUrls = {"/login", "/home", "/", "/css/**", "/js/**", "/img/**"};

    private final AuthenticationConfiguration authenticationConfiguration;
    @Bean
    PasswordEncoder passwordEncoder() {
        // TODO 나중에 모듈 변경하기 -> BCryptPasswordEncoder()
        return new SimplePasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .addFilter(new CorsConfig().corsFilter())
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions(frameOption -> frameOption.sameOrigin())) // h2 사용을 위한 설정
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(formLogin -> formLogin.disable())
                .httpBasic(httpBasic -> httpBasic.disable())

                .addFilter(new JwtAuthenticationFilter(authenticationManager()))

                .authorizeHttpRequests((requests) -> requests
                                .requestMatchers(allowedUrls).permitAll()
                                .requestMatchers(PathRequest.toH2Console()).permitAll()
                                .anyRequest().authenticated() // 그 외 요청은 인증 필요
                )

                .logout((logout) -> logout.permitAll())
        ;

        return http.build();

    }


}
