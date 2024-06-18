package com.fullstack.servise;

import com.fullstack.model.Follower;
import com.fullstack.model.User;
import com.fullstack.repository.FollowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowerService {
    @Autowired
    private FollowerRepository followerRepository;

    public FollowerService(FollowerRepository followerRepository) {
        this.followerRepository = followerRepository;
    }

    public List<Follower> getFollowersByUser(User user_id) {
        return followerRepository.findByUser(user_id);
    }

    public Follower addFollower(User user_id, User followingUser_id) {
        Follower follower = new Follower();
        follower.setUserId(user_id);
        follower.setFollowing_UserId(followingUser_id);
        return followerRepository.save(follower);
    }

    public boolean removeFollower(Long followerId) {
        Follower follower = followerRepository.findById(followerId).orElse(null);
        if (follower != null) {
            followerRepository.delete(follower);
            return true;
        }
        return false;
    }
}
