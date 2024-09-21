package capstone.capstonetest.domain.postComment;

import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostCommentRepository {
    private final SqlSessionTemplate sql;

    public void addComment(PostComment postComment){
        sql.insert("PostComment.addComment", postComment);
    }

    public List<PostComment> findCommentByPostId(Long postId){
        return sql.selectList("PostComment.findCommentByPostId", postId);
    }

    public void updateComment(PostComment postComment){
        sql.update("PostComment.updateComment",postComment);
    }

    public void deleteComment(Long commentId){
        sql.delete("PostComment.deleteComment", commentId);
    }
}
