package com.naz1k1.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.naz1k1.model.Result;
import com.naz1k1.model.enums.ResultCode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private void writeJson(HttpServletResponse response, Result<?> result) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login", "/register", "/captcha").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .successHandler((request, response, authentication) -> {
                    // 登录成功，返回用户信息
                    writeJson(response, Result.success(authentication.getPrincipal()));
                })
                .failureHandler((request, response, exception) -> {
                    // 根据不同异常返回不同的错误信息
                    Result<?> result;
                    if (exception instanceof BadCredentialsException) {
                        result = Result.error("用户名或密码错误");
                    } else if (exception instanceof DisabledException) {
                        result = Result.error("账户已被禁用");
                    } else if (exception instanceof LockedException) {
                        result = Result.error("账户已被锁定");
                    } else {
                        result = Result.error("登录失败：" + exception.getMessage());
                    }
                    writeJson(response, result);
                })
                .and()
                .logout()
                .logoutSuccessHandler((request, response, authentication) -> {
                    writeJson(response, Result.success("退出成功"));
                })
                .and()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, exception) -> {
                    // 未登录
                    writeJson(response, Result.error(ResultCode.UNAUTHORIZED));
                })
                .accessDeniedHandler((request, response, exception) -> {
                    // 无权限
                    writeJson(response, Result.error(ResultCode.FORBIDDEN));
                })
                .and()
                .sessionManagement()
                .maximumSessions(1)
                .expiredSessionStrategy(event -> {
                    // 会话过期
                    writeJson(event.getResponse(), Result.error("会话已过期，请重新登录"));
                });

        return http.build();
    }
}