package com.min.koreaareaboard.user.dto;

public enum OAuthType {
    GOOGLE("구글"), KAKAO("카카오"), NAVER("네이버");

    private final String name;

    OAuthType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
