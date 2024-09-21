package capstone.capstonetest.service;

import capstone.capstonetest.domain.member.Member;
import capstone.capstonetest.domain.member.MemberRepository;
import capstone.capstonetest.web.login.LoginForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;

    public Member login(LoginForm loginForm){
        Member findMember = memberRepository.findById(loginForm);
        if(findMember != null){
            return findMember;
        } else {
            return null;
        }
    }
}
