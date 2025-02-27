package com.min.koreaareaboard.user.service;

import com.min.koreaareaboard.common.JwtTokenProvider;
import com.min.koreaareaboard.user.dto.response.SignInResultDto;
import com.min.koreaareaboard.user.dto.response.SignUpResultDto;
import com.min.koreaareaboard.user.entity.User;
import com.min.koreaareaboard.user.enums.CommonResponse;
import com.min.koreaareaboard.user.enums.UserRole;
import com.min.koreaareaboard.user.repository.UserRepository;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 회원가입 서비스
 * 회원가입, 로그인
 */
@Service
public class SignService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;
  @Autowired
  public SignService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtTokenProvider = jwtTokenProvider;
  }

  @Transactional
  public SignUpResultDto signUp(String email, String password, String nickname, String role) {
    Optional<User> existingUser = Optional.ofNullable(userRepository.findByEmail(email));
    if (existingUser.isPresent()) {
      throw new IllegalStateException("이미 존재하는 회원입니다.");
    }

    List<UserRole> userRole = role.equalsIgnoreCase("ADMIN")
        ? Arrays.asList(UserRole.ADMIN, UserRole.USER)
        : Collections.singletonList(UserRole.USER);

    User user = User.builder()
        .email(email)
        .password(passwordEncoder.encode(password))
        .nickname(nickname)
        .userRole(userRole)
        .build();

    User savedUser = userRepository.save(user);
    boolean success = !savedUser.getEmail().isEmpty();
    CommonResponse response = success ? CommonResponse.SUCCESS : CommonResponse.FAIL;

    return new SignUpResultDto(success, response.getCode(), response.getMessage());
  }

  public SignInResultDto signIn(String email, String password) {
    User user = userRepository.findByEmail(email);
    if(user == null) {
      throw new IllegalStateException("존재하지 않는 회원입니다.");
    }
    if(!passwordEncoder.matches(password, user.getPassword())) {
      throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
    }
    SignInResultDto signInResultDto = null;
    try {
      signInResultDto = SignInResultDto.builder()
          .token(jwtTokenProvider.createToken(String.valueOf(user.getEmail()),
              user.getUserRole()))
          .build();
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("JWT 토큰 생성중 에러가 발생했습니다.");
    }
    return  new SignInResultDto(signInResultDto.getToken(),
        CommonResponse.SUCCESS.getMessage(), true,
        CommonResponse.SUCCESS.getCode());

  }




}
