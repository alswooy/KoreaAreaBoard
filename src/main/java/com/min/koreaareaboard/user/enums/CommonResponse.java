package com.min.koreaareaboard.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonResponse {
  SUCCESS(200, "SUCCESS"), FAIL(400,"FAIL");
  int code;
  String message;
}
