package com.fullstack.controller;

import com.fullstack.model.Followed;
import com.fullstack.model.User;
import com.fullstack.servise.FollowedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/followed")
public class FollowedController {

    private final FollowedService followedService;

    @Autowired
    public FollowedController(FollowedService followedService) {
        this.followedService = followedService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Followed>> getFollowedByUser(@PathVariable("userId") Long userId) {
        User user = new User();
        user.setUserId(userId);
        List<Followed> followedUsers = followedService.getFollowedByUser(user);
        return new ResponseEntity<>(followedUsers, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Followed> addFollowed(@RequestParam("userId") Long userId,
                                                @RequestParam("followedUserId") Long followedUserId) {
        User user = new User();
        user.setUserId(userId);
        User followedUser = new User();
        followedUser.setUserId(followedUserId);
        Followed addedFollowed = followedService.addFollowed(user, followedUser);
        return new ResponseEntity<>(addedFollowed, HttpStatus.CREATED);
    }

    @DeleteMapping("/{followedId}/remove")
    public ResponseEntity<Void> removeFollowed(@PathVariable("followedId") Long followedId) {
        boolean removed = followedService.removeFollowed(followedId);
        if (removed) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
