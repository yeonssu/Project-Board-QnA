package com.qnaserver.dto;

import com.qnaserver.entity.Member;
import com.qnaserver.entity.Question;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;


public class QuestionDto {
    @Getter
    @Setter
    public static class Post {
        @NotBlank
        private String title;

        @NotBlank
        private String content;

        @NotBlank
        private String memberEmail;

        private Question.PublicSecret publicSecret;
    }

    @Getter
    @Setter
    public static class Response {
        private long questionId;

        private String title;

        private String content;

        private String memberEmail;

        private Question.QuestionStatus questionStatus;

        private Question.PublicSecret publicSecret;

        private LocalDateTime createdAt;
    }

    @Getter
    @Setter
    public static class Patch {
        private long questionId;

        private String title;

        private String content;

        @NotBlank
        private String memberEmail;

        private Question.PublicSecret publicSecret;

        private Question.QuestionStatus questionStatus;
    }
}
