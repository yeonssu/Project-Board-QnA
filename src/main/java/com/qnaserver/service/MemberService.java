package com.qnaserver.service;

import com.qnaserver.dto.MemberDto;
import com.qnaserver.entity.Member;
import com.qnaserver.mapper.MemberMapper;
import com.qnaserver.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper mapper;
    private final String ADMIN_EMAIL = "admin@gmail.com";

    public MemberService(MemberRepository memberRepository, MemberMapper mapper) {
        this.memberRepository = memberRepository;
        this.mapper = mapper;
    }

    public MemberDto.Response createMember(MemberDto.Post memberPostDto) {
        Member member = mapper.memberPostDtoToMember(memberPostDto);
        Member save = memberRepository.save(member);
        return mapper.memberToMemberResponseDto(save);
    }

//    public Object login(MemberDto.Login memberLoginDto) {
//        if (memberLoginDto.getEmail().equals(ADMIN_EMAIL)) {
//            MemberDto.Post admin = new MemberDto.Post();
//
//            createMember()
//            return null;
//        }
//        Member findMember = memberRepository.findByEmail(memberLoginDto.getEmail());
//
//
//        return null;
//    }
}
