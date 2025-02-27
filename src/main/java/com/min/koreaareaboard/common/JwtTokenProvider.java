package com.min.koreaareaboard.common;

import com.min.koreaareaboard.user.entity.UserDetails;
import com.min.koreaareaboard.user.enums.UserRole;
import com.min.koreaareaboard.user.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
  private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
  private final UserService userService;

  @Value("${spring.jwt.secret}")
  private String secretKey;
  private final static long tokenValidMillisecond = 1000L * 60 * 60;

  @PostConstruct
  protected void init(){
    LOGGER.info("[init] JwtTokenProvider secretKey 초기화 시작 {}", secretKey);
    secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));

    LOGGER.info("[init] JwtTokenProvider secretKey 초기화 완료 {}", secretKey);
  }

  public String createToken(String userUid, List<UserRole> roles) throws NoSuchAlgorithmException {
    LOGGER.info("[createToken] 토큰 생성 시작");
    Date now = new Date();

    MessageDigest digest = MessageDigest.getInstance("SHA-256");
    byte[] hashedKey = digest.digest(secretKey.getBytes());

    Map<String, List<String>> claims = new HashMap<>();
    claims.put("roles", roles.stream().collect(ArrayList::new, (list, role) -> list.add(role.name()), ArrayList::addAll));

    String jws = Jwts.builder()
        .claims(claims)
        .setSubject(userUid)
        .setIssuedAt(now)
        .setExpiration(new Date(now.getTime() + tokenValidMillisecond))
        .signWith(SignatureAlgorithm.HS256, hashedKey)
        .compact();
    LOGGER.info("[createToken] 토큰 생성 완료");
    return jws;
  }

  public Authentication getAuthenticator(String token){
    LOGGER.info("[getAuthenticator] 토큰 인증 정보 조회 시작");
    UserDetails userDetails = userService.loadUserByUsername(this.getUsername(token));
    LOGGER.info("[getAuthenticator] 토큰 인증 정보 조회 완료 UserDetails: {}", userDetails.getAuthorities());
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

  public String getUsername(String token){
    LOGGER.info("[getUserName] 토큰 기반 회원 구별 정보 추출 시작");
    String info = Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getSubject();
    LOGGER.info("[getUserName] 토큰 기반 회원 구별 정보 추출 완료, info:{}",info);

    return info;
  }

  public String resolveToken(HttpServletRequest request){
    LOGGER.info("[resolveToken] HTTP 헤더에서 Token 값 추출 : {} ", request.getHeader("X-Auth-Token"));
    if(request.getHeader("X-Auth-Token") != null){
      return request.getHeader("X-Auth-Token");
    }
    return request.getHeader("X-Auth-Token");
  }

  public boolean validateToken(String token){
    LOGGER.info("[validateToken] 토큰 유효 체크 시작");
    try {
      Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(token);

      return !claims.getBody().getExpiration().before(new Date());
    } catch (ExpiredJwtException e) {
      LOGGER.info("[validateToken] 토큰 유효 체크 ExpiredJwtException 예외 발생 ");
      return false;
    } catch (UnsupportedJwtException e) {
      LOGGER.info("[validateToken] 토큰 유효 체크 UnsupportedJwtException 예외 발생 ");
      return false;
    } catch (MalformedJwtException e) {
      LOGGER.info("[validateToken] 토큰 유효 체크 MalformedJwtException 예외 발생 ");
      return false;
    } catch (IllegalArgumentException e) {
      LOGGER.info("[validateToken] 토큰 유효 체크 IllegalArgumentException 예외 발생 ");
      return false;
    }
  }

}
