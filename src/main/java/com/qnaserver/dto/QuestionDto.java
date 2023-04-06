package com.qnaserver.dto;

import com.qnaserver.entity.Member;
import com.qnaserver.entity.Question;
import com.qnaserver.entity.Reply;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;


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

        private List<Reply> replies;
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
