package com.fullstack.servise;

import com.fullstack.model.Post;
import com.fullstack.model.User;
import com.fullstack.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public List<Post> getPostsByUser(User user) {
        return postRepository.findByUser(user);
    }

    public Post createPost(User userId, String content, String imageUrl) {
        Post post = new Post();
        post.setUserId(userId);
        post.setContent(content);
        post.setImageUrl(imageUrl);
        post.setCreated_at(new Timestamp(System.currentTimeMillis())); // Current timestamp
        return postRepository.save(post);
    }

    public Post updatePost(Long postId, String content, String imageUrl) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post != null) {
            post.setContent(content);
            post.setImageUrl(imageUrl);
            return postRepository.save(post);
        }
        return null;
    }

    public boolean deletePost(Long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post != null) {
            postRepository.delete(post);
            return true;
        }
        return false;
    }
}
