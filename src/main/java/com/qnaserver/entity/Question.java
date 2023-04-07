package com.qnaserver.entity;

import com.qnaserver.enums.PublicSecret;
import com.qnaserver.enums.QuestionStatus;
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

}
