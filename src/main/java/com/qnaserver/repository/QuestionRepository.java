package com.qnaserver.repository;

import com.qnaserver.entity.Question;
import com.qnaserver.enums.QuestionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByQuestionStatusNotOrderByCreatedAtDesc(QuestionStatus questionStatus);
    List<Question> findByQuestionStatusNotOrderByCreatedAtAsc(QuestionStatus questionStatus);
    List<Question> findByQuestionStatusNot(QuestionStatus questionStatus);
}
