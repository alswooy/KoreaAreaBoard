package com.min.koreaareaboard.user.enums;

public enum UserRole {
    USER("사용자"), ADMIN("관리자");

    private final String name;

    UserRole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
