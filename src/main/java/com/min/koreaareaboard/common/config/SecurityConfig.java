package com.min.koreaareaboard.common.config;

import com.min.koreaareaboard.common.JwtAuthenticationFilter;
import com.min.koreaareaboard.common.JwtTokenProvider;
import com.min.koreaareaboard.common.UserAccessDeniedHandler;
import com.min.koreaareaboard.common.UserAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
  private final JwtTokenProvider jwtTokenProvider;

  @Autowired
  public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
    this.jwtTokenProvider = jwtTokenProvider;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  // Spring Security 6.1에서 deprecated되어 Customizer 패턴을 사용하는 방식으로 변경
  @Bean
  protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.httpBasic(httpSecurityHttpBasicConfigurer -> {
          httpSecurityHttpBasicConfigurer.disable();
        })
        .csrf(httpSecurityCsrfConfigurer -> {
          httpSecurityCsrfConfigurer.disable();
        })
        .sessionManagement(sessionManagement -> {
          sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        })
        .authorizeHttpRequests(authorizeRequests ->
            authorizeRequests.requestMatchers(HttpMethod.GET, "/board/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/board/**").hasRole("USER")
                .requestMatchers(HttpMethod.PUT, "/board/**").hasRole("USER")
                .requestMatchers(HttpMethod.DELETE, "/board/**").hasRole("USER")
                .requestMatchers("/user/**").hasRole("USER")
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("**exception**").permitAll()
                .requestMatchers("/sign/**").permitAll()
                .requestMatchers("/actuator/**").permitAll()
                .requestMatchers("/v3/**", "/swagger-ui/**").permitAll()
                .anyRequest().authenticated()
        )
        .exceptionHandling(exceptionHandlingConfigurer-> {
          exceptionHandlingConfigurer
              .accessDeniedHandler(new UserAccessDeniedHandler())
              .authenticationEntryPoint(new UserAuthenticationEntryPoint());
        })
        .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
        UsernamePasswordAuthenticationFilter.class);

    return httpSecurity.build();
  }
}
