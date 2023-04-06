package com.qnaserver.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long questionId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    private Member member;

    @Enumerated(EnumType.STRING)
    private QuestionStatus questionStatus = QuestionStatus.QUESTION_REGISTRATION;

    @Enumerated(EnumType.STRING)
    private PublicSecret publicSecret;

    @OneToMany(mappedBy = "question")
    private List<Reply> replies = new ArrayList<>();

    public void addReply (Reply reply) {
        this.replies.add(reply);
    }

    private LocalDateTime createdAt = LocalDateTime.now();

    public enum QuestionStatus {
        QUESTION_REGISTRATION("질문 등록 상태"),
        QUESTION_ANSWERED("답변 완료 상태"),
        QUESTION_DELETE("질문 삭제 상태");

        private String status;

        QuestionStatus(String status) {
            this.status = status;
        }
    }

    public enum PublicSecret {
        PUBLIC("공개글 상태"),
        SECRET("비밀글 상태");

        private String status;

        PublicSecret(String status) {
            this.status = status;
        }
    }

}
