package com.min.koreaareaboard.report;

public enum ReportStatus {
    ON_HOLD("대기 중"),
    PENDING("보류중"),
    APPROVED("승인됨"),
    REJECTED("거부됨");

    private final String description;

    ReportStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
