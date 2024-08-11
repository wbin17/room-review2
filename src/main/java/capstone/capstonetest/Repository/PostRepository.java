package capstone.capstonetest.Repository;

import capstone.capstonetest.domain.Post;

import java.util.List;

public interface PostRepository {
    Post save(Post post);

    Post findById(long id);

    List<Post> findAll();

    void update(Long postId, Post updateParam);
}
