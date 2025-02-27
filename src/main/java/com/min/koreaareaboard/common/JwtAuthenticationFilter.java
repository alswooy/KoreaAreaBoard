package com.min.koreaareaboard.common;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * JWT 토큰을 사용하여 요청을 필터링하는 역할
 * 요청에서 JWT 토큰을 추출, 토큰이 유효한지 인증 객체를 생성하여 SecurityContext에 저장
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private final JwtTokenProvider jwtTokenProvider;
  public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
    this.jwtTokenProvider = jwtTokenProvider;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String token = jwtTokenProvider.resolveToken(request);
    if (token != null && jwtTokenProvider.validateToken(token)) {
      Authentication authenticator = jwtTokenProvider.getAuthenticator(token);
      SecurityContextHolder.getContext().setAuthentication(authenticator);
    }
    filterChain.doFilter(request, response);
  }
}
