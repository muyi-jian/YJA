package com.yj.admin.config;


import com.yj.admin.filter.CaptchaValidationFilter;
import com.yj.admin.filter.JwtAuthenticationTokenFilter;
import com.yj.admin.security.MyAccessDeniedHandler;
import com.yj.admin.security.MyAuthenticationEntryPoint;
import com.yj.core.common.constant.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private MyAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private MyAccessDeniedHandler accessDeniedHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requestMatcherRegistry ->
                requestMatcherRegistry.requestMatchers(SecurityConstants.LOGIN_PATH).permitAll()
                        .anyRequest().authenticated()
        );

        http.exceptionHandling(httpSecurityExceptionHandlingConfigurer ->
                httpSecurityExceptionHandlingConfigurer
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler)
        );

        //http.exceptionHandling(e->e.accessDeniedPage("/noAuth"));

        http.csrf(c -> c.disable());

        http.cors(e -> e.disable());
        // 验证码校验过滤器
        http.addFilterBefore(new CaptchaValidationFilter(), UsernamePasswordAuthenticationFilter.class);

        // 登陆前获取token并校验
        http.addFilterBefore(new JwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * 不走过滤器链的放行配置
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers(
                        "/api/auth/captcha",
                        "/webjars/**",
                        "/doc.html",
                        "/swagger-resources/**",
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/ws/**",
                        "/ws-app/**"
                );
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
        //return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        //return  NoOpPasswordEncoder.getInstance();
    }
    /**
     * AuthenticationManager 手动注入
     *
     * @param authenticationConfiguration 认证配置
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
