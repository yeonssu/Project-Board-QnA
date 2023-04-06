package com.qnaserver.controller;

import com.qnaserver.dto.QuestionDto;
import com.qnaserver.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    public ResponseEntity<QuestionDto.Response> createQuestion(@RequestBody QuestionDto.Post questionPostDto) {
        return new ResponseEntity<>(questionService.createQuestion(questionPostDto), HttpStatus.CREATED);
    }

    @PatchMapping("{id}")
    public ResponseEntity<QuestionDto.Response> modifyQuestion(@PathVariable("id") Long questionId, @RequestBody QuestionDto.Patch questionPatchDto) {
        return new ResponseEntity<>(questionService.modifyQuestion(questionId, questionPatchDto), HttpStatus.OK);
    }
}
