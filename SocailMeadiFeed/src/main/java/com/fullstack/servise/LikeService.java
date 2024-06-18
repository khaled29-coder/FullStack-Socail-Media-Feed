package com.fullstack.servise;

import com.fullstack.model.Like;
import com.fullstack.model.Post;
import com.fullstack.model.User;
import com.fullstack.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class LikeService {
    @Autowired
    private LikeRepository likeRepository;

    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public List<Like> getLikesByPost(Post post) {
        return likeRepository.findByPost(post);
    }

    public Like createLike(Post post_id, User user_id, int likes) {
        Like like = new Like();
        like.setPostId(post_id);
        like.setUserId(user_id);
        like.setLikes(likes);
        like.setCreated_at(new Timestamp(System.currentTimeMillis())); // Current timestamp
        return likeRepository.save(like);
    }

    public boolean deleteLike(Long likeId) {
        Like like = likeRepository.findById(likeId).orElse(null);
        if (like != null) {
            likeRepository.delete(like);
            return true;
        }
        return false;
    }
}
