package com.qnaserver.controller;

import com.qnaserver.dto.LoginDto;
import com.qnaserver.dto.PageResponseDto;
import com.qnaserver.dto.QuestionDto;
import com.qnaserver.enums.SortType;
import com.qnaserver.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/questions")
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

    @GetMapping("{id}")
    public ResponseEntity<QuestionDto.Response> getQuestion(@PathVariable("id") Long questionId, @RequestBody LoginDto loginDto) {
        return new ResponseEntity<>(questionService.getQuestion(questionId, loginDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PageResponseDto> getAllQuestion(@RequestParam int page, @RequestParam int size,
                                                          @RequestParam SortType sortType,
                                                          @RequestBody LoginDto loginDto) {
        List<QuestionDto.Response> allQuestion = questionService.getAllQuestion(page, size, sortType, loginDto);
        return new ResponseEntity<>(new PageResponseDto<>(allQuestion, page, size), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<QuestionDto.Response> deleteQuestion(@PathVariable("id") Long questionId, @RequestBody LoginDto loginDto) {
        return new ResponseEntity<>(questionService.deleteQuestion(questionId, loginDto), HttpStatus.OK);
    }
}
