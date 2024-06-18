package com.fullstack.controller;

import com.fullstack.model.Post;
import com.fullstack.model.User;
import com.fullstack.servise.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable("id") Long id) {
        Post post = postService.getPostById(id);
        if (post != null) {
            return new ResponseEntity<>(post, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@RequestParam("userId") User userId,
                                           @RequestParam("content") String content,
                                           @RequestParam(value = "imageUrl", required = false) String imageUrl) {
        Post createdPost = postService.createPost(userId, content, imageUrl);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Post> updatePost(@PathVariable("id") Long id,
                                           @RequestParam("content") String content,
                                           @RequestParam(value = "imageUrl", required = false) String imageUrl) {
        Post updatedPost = postService.updatePost(id, content, imageUrl);
        if (updatedPost != null) {
            return new ResponseEntity<>(updatedPost, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deletePost(@PathVariable("id") Long id) {
        boolean deleted = postService.deletePost(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
