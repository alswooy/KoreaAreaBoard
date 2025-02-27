package com.min.koreaareaboard.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SignInResultDto extends SignUpResultDto {
  private String token;
  @Builder
  public SignInResultDto(String token, String msg, boolean success,int code) {
    super(success, code, msg);
    this.token = token;
  }

}
