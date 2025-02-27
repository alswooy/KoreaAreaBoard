package com.min.koreaareaboard.utill;

public enum CommonStatus {
    DELETE("삭제"), PUBLIC("공개"), PRIVATE("비공개");

    private final String status;
    CommonStatus(String name){
        this.status = name;
    }
    public String getStatus(){
        return status;
    }
}
