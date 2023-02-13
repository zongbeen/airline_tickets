package org.airlineTickets.repository;

import org.airlineTickets.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);// 회원가입 시 이메일을 통해 중복 회원을 검사
}
