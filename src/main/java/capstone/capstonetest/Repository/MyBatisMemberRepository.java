package capstone.capstonetest.Repository;

import capstone.capstonetest.domain.member.Member;
import capstone.capstonetest.web.login.LoginForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MyBatisMemberRepository {
    private final SqlSessionTemplate sql;


    public void save(Member member){
        sql.insert("Member.save",member);
    }

    public Member findBYId(long id){
        return sql.selectOne("Member.findById", id);
    }

//    public Optional<Member> findByLoginId(String loginId){
//        List<Member> all = findAll();
////        return findAll().stream()
////                .filter(m -> m.getLoginId().equals(loginId))
////                .findFirst();
//       // return (Optional<Member>) sql.selectOne("Member.findByLoginId", loginId);
//        Member member = sql.selectOne("Member.findByLoginId", loginId);
//        if(member == null){
//            return null;
//        }
//        else {
//            return Optional.ofNullable(member);
//
//        }
//    }

    public Member findByLoginId(LoginForm loginForm){

        return sql.selectOne("Member.findByLoginId", loginForm);
       // log.info("member = {}", member);

    }

    public List<Member> findAll(){
        return sql.selectList("Member.findAll");
       // return new ArrayList<>(store.values());
    }
}
