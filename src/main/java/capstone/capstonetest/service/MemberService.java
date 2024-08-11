package capstone.capstonetest.service;

import capstone.capstonetest.domain.member.Member;
import capstone.capstonetest.Repository.MyBatisMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MyBatisMemberRepository memberRepository;

    public void save(Member member){
        memberRepository.save(member);
    }
}
