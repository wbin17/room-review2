package capstone.capstonetest.Repository;

import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyBatisPostRepository {
    private final SqlSessionTemplate sql;

//    public void save(Post post){
//        sql.insert("Board.save", post);//객체 두개이상 넘기려면 해쉬맵으로 넘겨
//    }
//
//    public List<Post> findAll() {
//        return sql.selectList("Board.findAll");
//    }
//
//    public Post findById(Long postId) {
//        return sql.selectOne("Board.findById", postId);
//    }
//
//    public Post findByContent(Post post) {
//        return sql.selectOne("Board.findByContent",post);
//    }
//
//    public void update(Post post) {
//        sql.update("Board.update", post);
//    }
//
//    public void delete(Long postId) {
//        sql.delete("Board.delete",postId);
//    }

//    public void updateHits(Long postId) {
//        sql.update("Board.updateHits", postId);
//    }
}
