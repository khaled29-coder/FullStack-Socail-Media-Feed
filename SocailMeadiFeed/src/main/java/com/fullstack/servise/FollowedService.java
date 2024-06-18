package com.fullstack.servise;

import com.fullstack.model.Followed;
import com.fullstack.model.User;
import com.fullstack.repository.FollowedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowedService {
    @Autowired
    private FollowedRepository followedRepository;

    public FollowedService(FollowedRepository followedRepository) {
        this.followedRepository = followedRepository;
    }

    public List<Followed> getFollowedByUser(User user) {
        return followedRepository.findByUser(user);
    }

    public Followed addFollowed(User user, User followedUser) {
        Followed followed = new Followed();
        followed.setUserId(user);
        followed.setFollowed_UserId(followedUser);
        return followedRepository.save(followed);
    }

    public boolean removeFollowed(Long followerId) {
        Followed followed = followedRepository.findById(followerId).orElse(null);
        if (followed != null) {
            followedRepository.delete(followed);
            return true;
        }
        return false;
    }
}
