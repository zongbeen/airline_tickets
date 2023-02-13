package org.airlineTickets.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.airlineTickets.constant.Role;
import org.airlineTickets.dto.MemberFormDto;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "member")
@Getter @Setter @ToString
public class Member {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long memberId;

    private String memberName;

    @Column(unique = true)
    private String memberEmail;

    private String memberPassword;

    private String memberAddress;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setMemberName(memberFormDto.getMemberName());
        member.setMemberEmail(memberFormDto.getMemberEmail());
        member.setMemberAddress(memberFormDto.getMemberAddress());

        //SecurityConfig에서 등록한 Bean을 파라미터로 넘겨서 비밀번호를 암호화
        String password = passwordEncoder.encode(memberFormDto.getMemberPassword());
        member.setMemberPassword(password);

        member.setRole(Role.ADMIN);
        return member;
    }
}
