package com.qnaserver.entity;

import com.qnaserver.enums.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    // @Enumerated(EnumType.STRING)
    // private Role role = Role.USER;

    // List, Set 같은 컬렉션 타입의 필드는 @ElementCollection 를 추가하면,
    // 사용자 등록 시, 사용자의 권한을 등록하기 위한 권한 테이블을 생성하며
    // User 권한 정보와 관련된 별도의 엔티티 클래스를 생성하지 않아도 간단하게 매핑 처리가 된다
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

}
