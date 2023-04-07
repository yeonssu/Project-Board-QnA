package com.qnaserver.enums;

public enum QuestionStatus {
    QUESTION_REGISTRATION("질문 등록 상태"),
    QUESTION_ANSWERED("답변 완료 상태"),
    QUESTION_DELETE("질문 삭제 상태");

    private String status;

    QuestionStatus(String status) {
        this.status = status;
    }
}
