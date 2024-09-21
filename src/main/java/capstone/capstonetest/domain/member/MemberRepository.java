package capstone.capstonetest.domain.member;

import capstone.capstonetest.web.login.LoginForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MemberRepository {

    private final SqlSessionTemplate sql;

    public void save(Member member){
        sql.insert("Member.save", member);
    }

    public int isMemberIdAvailable(String memberId) {
        return sql.selectOne("Member.isMemberIdAvailable", memberId);
    }

    public Member findById(LoginForm loginForm) {
        return sql.selectOne("Member.findById", loginForm);
    }

    public Member findMemberByMemberId(String memberId){
//        List<Member> members = sql.selectList("Member.findMemberWithBoards", memberId);
        return sql.selectOne("Member.findMemberByMemberId", memberId);
    }
}
