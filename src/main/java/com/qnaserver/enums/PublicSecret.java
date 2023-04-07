package com.qnaserver.enums;

public enum PublicSecret {
    PUBLIC("공개글 상태"),
    SECRET("비밀글 상태");

    private String status;

    PublicSecret(String status) {
        this.status = status;
    }
}