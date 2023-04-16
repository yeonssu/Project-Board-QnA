package com.qnaserver.dto;

import com.qnaserver.enums.Role;
import lombok.*;

import java.util.List;

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
    @AllArgsConstructor
    public static class Response {
        private long memberId;
        private String email;
        private String name;
        private List<String> roles;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TokenResponse {
        private long memberId;
        private String email;
        private String name;
        private List<String> roles;
        private String token;
    }

    @Getter
    @NoArgsConstructor
    public static class Login {
        private String email;
        private String password;
    }
}
