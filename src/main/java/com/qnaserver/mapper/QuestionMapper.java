package com.qnaserver.mapper;

import com.qnaserver.dto.QuestionDto;
import com.qnaserver.entity.Member;
import com.qnaserver.entity.Question;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionMapper {
    public Question questionPostToQuestion(QuestionDto.Post questionPostDto, Member member) {
        Question question = new Question();
        question.setTitle(questionPostDto.getTitle());
        question.setMember(member);
        question.setContent(questionPostDto.getContent());
        question.setPublicSecret(questionPostDto.getPublicSecret());

        return question;
    }

    public QuestionDto.Response questionToQuestionResponse(Question question) {
        QuestionDto.Response response = new QuestionDto.Response();

        response.setMemberEmail(question.getMember().getEmail());
        response.setQuestionId(question.getQuestionId());
        response.setTitle(question.getTitle());
        response.setContent(question.getContent());
        response.setQuestionStatus(question.getQuestionStatus());
        response.setPublicSecret(question.getPublicSecret());
        response.setCreatedAt(question.getCreatedAt());
        response.setReply(question.getReply());

        return response;
    }

    public Question questionPatchToQuestion(QuestionDto.Patch questionPatchDto) {
        Question question = new Question();
        question.setQuestionId(questionPatchDto.getQuestionId());
        question.setQuestionStatus(questionPatchDto.getQuestionStatus());
        question.setTitle(questionPatchDto.getTitle());
        question.setContent(questionPatchDto.getContent());
        question.setPublicSecret(questionPatchDto.getPublicSecret());

        return question;
    }

    public List<QuestionDto.Response> questionListToQuestionResponse(List<Question> allQuestion) {
        List<QuestionDto.Response> responseList = new ArrayList<>();

//        for (Question question : allQuestion) {
//            if (!question.getQuestionStatus().equals(Question.QuestionStatus.QUESTION_DELETE)) responseList.add(questionToQuestionResponse(question));
//        }

        for (Question question : allQuestion) {
            responseList.add(questionToQuestionResponse(question));
        }
        return responseList;
    }
}
