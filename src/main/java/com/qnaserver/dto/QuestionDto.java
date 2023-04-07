package com.qnaserver.dto;

import com.qnaserver.enums.PublicSecret;
import com.qnaserver.entity.Reply;
import com.qnaserver.enums.QuestionStatus;
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

        private PublicSecret publicSecret;
    }

    @Getter
    @Setter
    public static class Response {
        private long questionId;

        private String title;

        private String content;

        private String memberEmail;

        private QuestionStatus questionStatus;

        private PublicSecret publicSecret;

        private LocalDateTime createdAt;

        private Reply reply;
    }

    @Getter
    @Setter
    public static class Patch {
        private long questionId;

        private String title;

        private String content;

        @NotBlank
        private String memberEmail;

        private PublicSecret publicSecret;

        private QuestionStatus questionStatus;
    }
}
