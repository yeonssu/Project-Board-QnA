package com.qnaserver.repository;

import com.qnaserver.dto.MemberDto;
import com.qnaserver.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);
}
