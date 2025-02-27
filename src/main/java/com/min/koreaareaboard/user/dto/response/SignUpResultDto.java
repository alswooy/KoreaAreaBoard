package com.min.koreaareaboard.user.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SignUpResultDto {
  private boolean success;
  private int code;
  private String msg;
}
