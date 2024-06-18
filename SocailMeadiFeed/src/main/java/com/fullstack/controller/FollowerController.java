package com.fullstack.controller;

import com.fullstack.model.Follower;
import com.fullstack.model.User;
import com.fullstack.servise.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class FollowerController {

    private final FollowerService followerService;

    @Autowired
    public FollowerController(FollowerService followerService) {
        this.followerService = followerService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Follower>> getFollowersByUser(@PathVariable("userId") Long userId) {
        User user = new User();
        user.setUserId(userId);
        List<Follower> followers = followerService.getFollowersByUser(user);
        return new ResponseEntity<>(followers, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Follower> addFollower(@RequestParam("userId") Long userId,
                                                @RequestParam("followingUserId") Long followingUserId) {
        User user = new User();
        user.setUserId(userId);
        User followingUser = new User();
        followingUser.setUserId(followingUserId);
        Follower addedFollower = followerService.addFollower(user, followingUser);
        return new ResponseEntity<>(addedFollower, HttpStatus.CREATED);
    }

    @DeleteMapping("/{followerId}/remove")
    public ResponseEntity<Void> removeFollower(@PathVariable("followerId") Long followerId) {
        boolean removed = followerService.removeFollower(followerId);
        if (removed) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
