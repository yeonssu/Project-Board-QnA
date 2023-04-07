package com.qnaserver.dto;

import com.qnaserver.entity.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class ReplyDto {
    @Getter
    public static class Post {
        private String memberEmail;

        @NotBlank
        private String content;
    }

    @Getter
    public static class Patch {
        private String memberEmail;

        @NotBlank
        private String content;
    }
}
