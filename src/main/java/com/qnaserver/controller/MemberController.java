package com.qnaserver.controller;

import com.qnaserver.config.JwtTokenProvider;
import com.qnaserver.dto.MemberDto;
import com.qnaserver.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/signup")
    public ResponseEntity<MemberDto.Response> createMember(@RequestBody MemberDto.Post memberPostDto) {
        return new ResponseEntity<>(memberService.createMember(memberPostDto), HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<MemberDto.Response> login(@RequestBody MemberDto.Login LoginDto) {
        MemberDto.Response response = memberService.login(LoginDto);

        String token = jwtTokenProvider.createToken(response.getEmail(), response.getRoles());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + token);

        return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
    }
}
