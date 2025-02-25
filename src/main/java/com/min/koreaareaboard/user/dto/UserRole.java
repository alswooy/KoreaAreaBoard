package com.min.koreaareaboard.user.dto;

public enum UserRole {
    USER("사용자"), ADMIN("관리자");

    UserRole(String name) {
        name = this.name();
    }
}
