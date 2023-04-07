package com.qnaserver.service;

import com.qnaserver.dto.LoginDto;
import com.qnaserver.dto.ReplyDto;
import com.qnaserver.entity.Question;
import com.qnaserver.entity.Reply;
import com.qnaserver.enums.QuestionStatus;
import com.qnaserver.mapper.ReplyMapper;
import com.qnaserver.repository.QuestionRepository;
import com.qnaserver.repository.ReplyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReplyService {
    private final QuestionRepository questionRepository;
    private final ReplyRepository replyRepository;
    private final ReplyMapper mapper;

    private final String ADMIN_EMAIL = "admin@gmail.com";

    public ReplyService(QuestionRepository questionRepository, ReplyRepository replyRepository, ReplyMapper mapper) {
        this.questionRepository = questionRepository;
        this.replyRepository = replyRepository;
        this.mapper = mapper;
    }

    public void createReply(Long questionId, ReplyDto.Post replyPostDto) {
        if (!replyPostDto.getMemberEmail().equals(ADMIN_EMAIL)) {
            throw new RuntimeException("답변은 관리자만 등록할 수 있습니다.");
        }

        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        Question question = optionalQuestion.orElseThrow(() -> new RuntimeException("해당 게시물이 존재하지 않습니다."));

        if (question.getQuestionStatus().equals(QuestionStatus.QUESTION_DELETE)) {
            throw new RuntimeException("해당 게시물은 삭제된 게시물입니다.");
        } else if (question.getQuestionStatus().equals(QuestionStatus.QUESTION_ANSWERED)) {
            throw new RuntimeException("해당 게시물은 이미 답변 완료되었습니다.");
        }

        Reply reply = mapper.replyPostToReply(replyPostDto);
        question.setReply(reply);
        question.setQuestionStatus(QuestionStatus.QUESTION_ANSWERED);
        questionRepository.save(question);
    }

    public void modifyReply(Long questionId, ReplyDto.Patch replyPatchDto) {
        if (!replyPatchDto.getMemberEmail().equals(ADMIN_EMAIL)) {
            throw new RuntimeException("답변은 관리자만 수정할 수 있습니다.");
        }

        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        Question question = optionalQuestion.orElseThrow(() -> new RuntimeException("해당 게시물이 존재하지 않습니다."));

        if (question.getQuestionStatus().equals(QuestionStatus.QUESTION_DELETE)) {
            throw new RuntimeException("해당 게시물은 삭제된 게시물입니다.");
        } else if (!question.getQuestionStatus().equals(QuestionStatus.QUESTION_ANSWERED)) {
            throw new RuntimeException("해당 질문에 대한 답변이 존재하지 않습니다.");
        }

        Reply reply = question.getReply();
        reply.setContent(replyPatchDto.getContent());
        question.setReply(reply);
        question.setReply(reply);

        questionRepository.save(question);
    }

    public void deleteReply(Long questionId, LoginDto loginDto) {
        if (!loginDto.getMemberEmail().equals(ADMIN_EMAIL)) {
            throw new RuntimeException("답변은 관리자만 삭제할 수 있습니다.");
        }

        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        Question question = optionalQuestion.orElseThrow(() -> new RuntimeException("해당 게시물이 존재하지 않습니다."));

        if (!question.getQuestionStatus().equals(QuestionStatus.QUESTION_ANSWERED)) {
            throw new RuntimeException("해당 질문에 대한 답변이 존재하지 않습니다.");
        }
        long deleteReplyId = question.getReply().getReplyId();

        question.setReply(null);
        question.setQuestionStatus(QuestionStatus.QUESTION_REGISTRATION);
        questionRepository.save(question);
        replyRepository.deleteById(deleteReplyId);
    }
}
