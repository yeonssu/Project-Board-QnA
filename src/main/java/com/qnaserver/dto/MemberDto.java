package com.qnaserver.dto;

import com.qnaserver.entity.Member;
import lombok.*;

public class MemberDto {
    @Getter
    @Setter
    @NoArgsConstructor
    public static class Post {
        private String email;
        private String password;
        private String name;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Login {
        private String email;
        private String password;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private long memberId;
        private String email;
        private String name;
        private Member.Role role;
    }
}
