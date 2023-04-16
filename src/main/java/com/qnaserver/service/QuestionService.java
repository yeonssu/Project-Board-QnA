package com.qnaserver.service;

import com.qnaserver.dto.LoginDto;
import com.qnaserver.dto.QuestionDto;
import com.qnaserver.entity.Member;
import com.qnaserver.enums.PublicSecret;
import com.qnaserver.entity.Question;
import com.qnaserver.enums.QuestionStatus;
import com.qnaserver.enums.SortType;
import com.qnaserver.mapper.QuestionMapper;
import com.qnaserver.repository.MemberRepository;
import com.qnaserver.repository.QuestionRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    private final MemberRepository memberRepository;
    private final QuestionRepository questionRepository;
    private final QuestionMapper mapper;

    private final String ADMIN_EMAIL = "admin@gmail.com";

    public QuestionService(MemberRepository memberRepository, QuestionRepository questionRepository, QuestionMapper mapper) {
        this.memberRepository = memberRepository;
        this.questionRepository = questionRepository;
        this.mapper = mapper;
    }

    public QuestionDto.Response createQuestion(QuestionDto.Post questionPostDto) {
        Question question = mapper.questionPostToQuestion(questionPostDto);
        Question save = questionRepository.save(question);
        return mapper.questionToQuestionResponse(save);
    }

    public QuestionDto.Response modifyQuestion(Long questionId, QuestionDto.Patch questionPatchDto) {
        questionPatchDto.setQuestionId(questionId);
        Question question = mapper.questionPatchToQuestion(questionPatchDto);

        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        Question findQuestion = optionalQuestion.orElseThrow(() -> new RuntimeException("게시물이 등록되어있지 않습니다."));

//        if (findQuestion.getQuestionStatus().equals(QuestionStatus.QUESTION_ANSWERED)) {
//            throw new RuntimeException("답변 완료 상태의 질문은 수정할 수 없습니다.");
//        }
//
//        if (findQuestion.getQuestionStatus().equals(QuestionStatus.QUESTION_DELETE)) {
//            throw new RuntimeException("이미 삭제한 질문은 수정/삭제할 수 없습니다.");
//        }

        Optional.ofNullable(question.getTitle())
                .ifPresent(title -> findQuestion.setTitle(title));
        Optional.ofNullable(question.getContent())
                .ifPresent(content -> findQuestion.setContent(content));
        Optional.ofNullable(question.getQuestionStatus())
                .ifPresent(status -> findQuestion.setQuestionStatus(status));
        Optional.ofNullable(question.getPublicSecret())
                .ifPresent(publicSecret -> findQuestion.setPublicSecret(publicSecret));

        // admin이 아닌데 "답변완료"로 바꿀 경우
//        if (!questionPatchDto.getMemberEmail().equals(ADMIN_EMAIL)){
//            if (findQuestion.getQuestionStatus().equals(QuestionStatus.QUESTION_ANSWERED)) {
//                throw new RuntimeException("답변 완료로의 변경은 관리자만 가능합니다.");
//            }
//
//            if (!findQuestion.getMember().getEmail().equals(questionPatchDto.getMemberEmail())) {
//                throw new RuntimeException("질문을 등록한 회원만 수정할 수 있습니다.");
//            }
//        }

        Question save = questionRepository.save(findQuestion);
        return mapper.questionToQuestionResponse(save);
    }

    public QuestionDto.Response getQuestion(Long questionId, LoginDto loginDto) {
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        Question findQuestion = optionalQuestion.orElseThrow(() -> new RuntimeException("해당 게시물이 존재하지 않습니다."));

        if (findQuestion.getQuestionStatus().equals(QuestionStatus.QUESTION_DELETE)) {
            throw new RuntimeException("이미 삭제된 게시물 입니다.");
        }

        String email = loginDto.getMemberEmail();

        if (findQuestion.getPublicSecret().equals(PublicSecret.SECRET)) {
            if (!findQuestion.getMember().getEmail().equals(email) || !email.equals(ADMIN_EMAIL)) {
                throw new RuntimeException("비밀글이므로 질문을 등록한 회원과 관리자만 조회할 수 있습니다.");
            }
        }

        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        if (!email.equals(ADMIN_EMAIL)){
            optionalMember.orElseThrow(() -> new RuntimeException("등록된 회원이 아닙니다."));
        }

        return mapper.questionToQuestionResponse(findQuestion);
    }

    public QuestionDto.Response deleteQuestion(Long questionId, LoginDto loginDto) {
        String email = loginDto.getMemberEmail();
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        Question findQuestion = optionalQuestion.orElseThrow(() -> new RuntimeException("해당 게시물이 존재하지 않습니다."));

        if (findQuestion.getQuestionStatus().equals(QuestionStatus.QUESTION_DELETE)) {
            throw new RuntimeException("이미 삭제 상태인 질문은 삭제할 수 없습니다.");
        }

        if (!findQuestion.getMember().getEmail().equals(email)) {
            throw new RuntimeException("질문 삭제는 질문을 등록한 회원만 가능합니다.");
        }

        findQuestion.setQuestionStatus(QuestionStatus.QUESTION_DELETE);
        Question save = questionRepository.save(findQuestion);

        return mapper.questionToQuestionResponse(save);
    }

    public List<QuestionDto.Response> getAllQuestion(int page, int size, SortType sortType, LoginDto loginDto) {
        String email = loginDto.getMemberEmail();

        Optional<Member> optionalMember = memberRepository.findByEmail(email);

        if (!email.equals(ADMIN_EMAIL)){
            optionalMember.orElseThrow(() -> new RuntimeException("등록된 회원이 아닙니다."));
        }

        Pageable pageable = PageRequest.of(page - 1, size);
//        Page<Question> findAll = questionRepository.findAll(pageable);
//        List<Question> allQuestion = findAll.getContent();
//        mapper.questionListToQuestionResponse(allQuestion);

        List<Question> questions = null;
        if (sortType.equals(SortType.LATEST)) {
            questions = questionRepository.findByQuestionStatusNotOrderByCreatedAtDesc(QuestionStatus.QUESTION_DELETE);
        } else if (sortType.equals(SortType.NEWEST)) {
            questions = questionRepository.findByQuestionStatusNotOrderByCreatedAtAsc(QuestionStatus.QUESTION_DELETE);
        }

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), questions.size());
        List<Question> subQuestion = questions.subList(start, end);

        return mapper.questionListToQuestionResponse(subQuestion);
    }
}
