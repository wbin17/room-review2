package capstone.capstonetest.service;

import capstone.capstonetest.domain.board.Board;
import capstone.capstonetest.domain.board.BoardRepository;
import capstone.capstonetest.web.board.PostDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {
    private final BoardRepository boardRepository;

    public Long save(Board board) {

        boardRepository.save(board);
//        log.info("id = {}",board.getId());
        return board.getId();
    }

    public List<Board> findAll(){
        return boardRepository.findAll();
    }

    public Board findById(Long postId) {
        return boardRepository.findById(postId);
    }

    public Board findBoardDetailById(Long postId){
        return boardRepository.findBoardDetailById(postId);
    }

    public List<Board> showReviews(Long roomId){
        return boardRepository.findReviewsByRoomId(roomId);
    }

    public void editPost(Board board){
        boardRepository.updatePost(board);
    }

    public void deletePost(Long postId) {
        boardRepository.deletePost(postId);
    }

//
//    public Post findByContent(Post post) {
//        return postRepository.findByContent(post);
//    }
//
//    public void update(Post post) {
//        postRepository.update(post);
//    }
//
//    public void delete(Long postId) {
//        postRepository.delete(postId);
//    }

//    public void updateHits(Long postId) {
//        postRepository.updateHits(postId);
//    }
}
