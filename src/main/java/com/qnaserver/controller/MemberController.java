package com.qnaserver.controller;

import com.qnaserver.dto.MemberDto;
import com.qnaserver.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<MemberDto.Response> createMember(@RequestBody MemberDto.Post memberPostDto) {
        return new ResponseEntity<>(memberService.createMember(memberPostDto), HttpStatus.CREATED);
    }

//    @PostMapping
//    public ResponseEntity<> loginMember(@RequestBody MemberDto.Login memberLoginDto){
//        return new ResponseEntity<>(memberService.login(memberLoginDto));
//    }
}
