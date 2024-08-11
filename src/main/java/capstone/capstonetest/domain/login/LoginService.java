package capstone.capstonetest.domain.login;

import capstone.capstonetest.Repository.MyBatisMemberRepository;
import capstone.capstonetest.domain.member.Member;
import capstone.capstonetest.domain.member.MemberRepository;
import capstone.capstonetest.web.login.LoginForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {
    //private final MemberRepository memberRepository;
    private final MyBatisMemberRepository myBatisMemberRepository;

//    public Member login(String loginId, String password){
//        Optional<Member> findMemberOptional = myBatisMemberRepository.findByLoginId(loginId);
//
//        return findMemberOptional
//                .filter(m -> m.getPassword().equals(password))
//                .orElse(null);
//    }

    public Member login(LoginForm loginForm){
      //  Optional<Member> findMemberOptional = myBatisMemberRepository.findByLoginId(loginId);
        Member findMember = myBatisMemberRepository.findByLoginId(loginForm);

        if(findMember != null){
            return findMember;
        } else{
           return null;
        }

//        if(findMember.getPassword().equals(loginForm.getPassword())){
//            return findMember;
//        }
//        else {
//            return null;
//        }

    }

}
