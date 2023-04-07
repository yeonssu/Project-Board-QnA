package com.qnaserver.controller;

import com.qnaserver.dto.LoginDto;
import com.qnaserver.dto.ReplyDto;
import com.qnaserver.service.ReplyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/reply")
public class ReplyController {
    private final ReplyService replyService;

    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @PostMapping("{question-id}")
    public ResponseEntity createReply(@PathVariable("question-id") Long questionId, @RequestBody ReplyDto.Post replyPostDto) {
        replyService.createReply(questionId, replyPostDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("{question-id}")
    public ResponseEntity modifyReply(@PathVariable("question-id") Long questionId, @RequestBody ReplyDto.Patch replyPatchDto) {
        replyService.modifyReply(questionId, replyPatchDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{question-id}")
    public ResponseEntity deleteReply(@PathVariable("question-id") Long questionId, @RequestBody LoginDto loginDto) {
        replyService.deleteReply(questionId, loginDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
