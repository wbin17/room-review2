package capstone.capstonetest.service;

import capstone.capstonetest.Repository.MyBatisPostRepository;
import capstone.capstonetest.Repository.PostMemoryRepository;
import capstone.capstonetest.Repository.PostRepository;
import capstone.capstonetest.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final MyBatisPostRepository postRepository;

    public void save(Post post) {
        postRepository.save(post);
    }

    public List<Post> findAll(){
        return postRepository.findAll();
    }

    public Post findById(Long postId) {
        return postRepository.findById(postId);
    }

    public Post findByContent(Post post) {
        return postRepository.findByContent(post);
    }

    public void update(Post post) {
        postRepository.update(post);
    }

    public void delete(Long postId) {
        postRepository.delete(postId);
    }

//    public void updateHits(Long postId) {
//        postRepository.updateHits(postId);
//    }
}
