package capstone.capstonetest.domain.board;

import capstone.capstonetest.domain.board.Board;
import capstone.capstonetest.web.board.PostDto;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final SqlSessionTemplate sql;

    public Board findById(Long postId) {
        return sql.selectOne("Board.findById", postId);
    }

    public Board findBoardDetailById(Long postId){
        return sql.selectOne("Board.findBoardDetailById", postId);
    }

    public List<Board> findBoardListByWriterId(String writerId){
        return sql.selectList("Board.findBoardListByWriterId",writerId);
    }

    public List<Board> findReviewsByRoomId(Long roomId){
        return sql.selectList("Board.findReviewsByRoomId", roomId);
    }

    public void save(Board board){
        sql.insert("Board.save", board);
    }

    public List<Board> findAll() {
        return sql.selectList("Board.findAll");
    }

    public void updatePost(Board board){
        sql.update("Board.updatePost", board);
    }

    public void deletePost(Long postId) {
        sql.delete("Board.deletePost",postId);
    }
}
