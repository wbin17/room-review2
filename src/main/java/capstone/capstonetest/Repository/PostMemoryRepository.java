package capstone.capstonetest.Repository;

import capstone.capstonetest.domain.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostMemoryRepository implements PostRepository{
    private static final Map<Long, Post> store = new HashMap<>();
    private static long sequence = 0L;
    @Override
    public Post save(Post post) {
        post.setId(++sequence);
        store.put(post.getId(), post);
        return post;
    }

    @Override
    public Post findById(long id) {
        return store.get(id);
    }

    @Override
    public List<Post> findAll() {
        return new  ArrayList<>(store.values());

    }

    @Override
    public void update(Long postId, Post updateParam) {
        Post findPost = findById(postId);
        findPost.setTitle(updateParam.getTitle());
        findPost.setContent((updateParam.getContent()));
        findPost.setAddress(updateParam.getAddress());

    }
}
