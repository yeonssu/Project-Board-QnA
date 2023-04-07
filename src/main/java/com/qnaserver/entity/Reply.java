package com.qnaserver.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Reply {
    @Id
    @GeneratedValue
    private long replyId;

    private String content;

    private LocalDateTime createdAt = LocalDateTime.now();
}
