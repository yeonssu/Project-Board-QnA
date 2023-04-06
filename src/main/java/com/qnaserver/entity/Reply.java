package com.qnaserver.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Reply {
    @Id
    @GeneratedValue
    private long replyId;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    @CreatedDate
    private LocalDateTime createdAt = LocalDateTime.now();

    @LastModifiedDate
    private LocalDateTime modifiedAt = LocalDateTime.now();
}
