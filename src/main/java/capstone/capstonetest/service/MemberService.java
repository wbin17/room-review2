package capstone.capstonetest.service;

import capstone.capstonetest.domain.board.Board;
import capstone.capstonetest.domain.board.BoardRepository;
import capstone.capstonetest.domain.member.Member;
import capstone.capstonetest.Repository.MyBatisMemberRepository;
import capstone.capstonetest.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    public void save(Member member){
        memberRepository.save(member);
    }

    public boolean isMemberIdAvailable(String memberId) {
        return memberRepository.isMemberIdAvailable(memberId) > 0;
    }

    public Member myPage(String memberId){
        Member member = memberRepository.findMemberByMemberId(memberId);
        List<Board> boardList = boardRepository.findBoardListByWriterId(memberId);
        member.setBoardList(boardList);
        return member;
    }
}
