package com.fullstack.controller;

import com.fullstack.model.Like;
import com.fullstack.model.Post;
import com.fullstack.model.User;
import com.fullstack.servise.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class LikeController {

    private final LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Like>> getLikesByPost(@PathVariable("postId") Long postId) {
        Post post = new Post();
        post.setPostId(postId);
        List<Like> likes = likeService.getLikesByPost(post);
        return new ResponseEntity<>(likes, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Like> createLike(@RequestParam("postId") Long postId,
                                           @RequestParam("userId") Long userId,
                                           @RequestParam("likes") int likes) {
        Post post = new Post();
        post.setPostId(postId);
        User user = new User();
        user.setUserId(userId);
        Like createdLike = likeService.createLike(post, user, likes);
        return new ResponseEntity<>(createdLike, HttpStatus.CREATED);
    }

    @DeleteMapping("/{likeId}/delete")
    public ResponseEntity<Void> deleteLike(@PathVariable("likeId") Long likeId) {
        boolean deleted = likeService.deleteLike(likeId);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
