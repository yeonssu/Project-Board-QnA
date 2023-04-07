package com.qnaserver.dto;

import com.qnaserver.enums.Role;
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
    @AllArgsConstructor
    public static class Response {
        private long memberId;
        private String email;
        private String name;
        private Role role;
    }
}
