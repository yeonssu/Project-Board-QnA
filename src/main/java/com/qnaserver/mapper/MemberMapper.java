package com.qnaserver.mapper;

import com.qnaserver.dto.MemberDto;
import com.qnaserver.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {
    public Member memberPostDtoToMember(MemberDto.Post memberPostDto) {
        Member member = new Member();

        member.setEmail(memberPostDto.getEmail());
        member.setPassword(memberPostDto.getPassword());
        member.setName(memberPostDto.getName());

        return member;
    }

    public MemberDto.Response memberToMemberResponseDto(Member member) {
        MemberDto.Response response = new MemberDto.Response();

        response.setMemberId(member.getMemberId());
        response.setEmail(member.getEmail());
        response.setName(member.getName());
        response.setRoles(member.getRoles());

        return response;
    }

    public MemberDto.TokenResponse memberToMemberTokenResponseDto(Member member, String token) {
        MemberDto.TokenResponse response = new MemberDto.TokenResponse();

        response.setMemberId(member.getMemberId());
        response.setEmail(member.getEmail());
        response.setName(member.getName());
        response.setRoles(member.getRoles());
        response.setToken(token);

        return response;
    }
}
