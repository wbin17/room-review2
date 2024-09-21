package capstone.capstonetest.service;

import capstone.capstonetest.domain.postComment.PostComment;
import capstone.capstonetest.domain.postComment.PostCommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostCommentService {
    private final PostCommentRepository postCommentRepository;


    public void addComment(PostComment postComment){
        postCommentRepository.addComment(postComment);
    }

    public List<PostComment> findCommentByPostId(Long postId){
        return postCommentRepository.findCommentByPostId(postId);
    }

    public void updateComment(PostComment postComment){
        postCommentRepository.updateComment(postComment);
    }


    public void deleteComment(Long commentId){
        postCommentRepository.deleteComment(commentId);
    }
}
