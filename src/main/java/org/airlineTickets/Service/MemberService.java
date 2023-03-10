package org.airlineTickets.Service;

import lombok.RequiredArgsConstructor;
import org.airlineTickets.entity.Member;
import org.airlineTickets.repository.MemberRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional //비즈니스 로직을 담당하는 서비스 계층 클래스에 선언. 로직을 처리하다가 에러가 발생하면 변경된 데이터를 로직을 수행하기 이전 상태로 콜백
@RequiredArgsConstructor //final, @NotNull이 붙은 필드에 생성자를 생성
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public Member saveMember(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }
    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByEmail(member.getMemberEmail());
        if(findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다."); //IllegalStateException : 객체 상태가 메서드 호출을 처리하기에 적절치 않을 때
        }
    }

    //implement UserDetailService 인터페이스의 loadUserByUsername() 메소드를 오버라이딩
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);

        if(member == null) {
            throw new UsernameNotFoundException(email);
        }

        return User.builder()
                .username(member.getMemberEmail())
                .password(member.getMemberPassword())
                .roles(member.getRole().toString())
                .build();
    }
}
