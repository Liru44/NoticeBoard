package com.noticeboard.Board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrfConfig) -> csrfConfig.disable())
                .authorizeRequests()
                    .requestMatchers("/", "/login", "/signup", "/boardList").permitAll()
                    .requestMatchers("/newBoard").authenticated()
                    .anyRequest().permitAll();
        http
                .formLogin(login -> login
                        .loginPage("/login")
                        .loginProcessingUrl("/login_proc")
                        .usernameParameter("id")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/boardList")
                        .failureUrl("/login?error=true")
                        .permitAll());
        http
                 .logout(logout -> logout //로그아웃 설정
                         .logoutUrl("/logout")
                         .logoutSuccessUrl("/")
                         .invalidateHttpSession(true));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
