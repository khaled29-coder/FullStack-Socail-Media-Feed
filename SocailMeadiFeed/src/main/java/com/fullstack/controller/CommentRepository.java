package com.fullstack.controller;

import com.fullstack.model.Comment;
import com.fullstack.model.Post;
import com.fullstack.model.User;
import com.fullstack.servise.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("")
public class CommentRepository {
    private final CommentService commentService;

    @Autowired
    public CommentRepository(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Comment>> getCommentsByPost(@PathVariable("postId") Long postId) {
        Post post = new Post();
        post.setPostId(postId);
        List<Comment> comments = commentService.getCommentsByPost(post);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Comment> createComment(@RequestParam("postId") Long post_id,
                                                 @RequestParam("userId") Long user_id,
                                                 @RequestParam("content") String content) {
        Post post = new Post();
        post.setPostId(post_id);
        User user = new User();
        user.setUserId(user_id);
        Comment createdComment = commentService.createComment(post, user, content);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/{commentId}/delete")
    public ResponseEntity<Void> deleteComment(@PathVariable("commentId") Long commentId) {
        boolean deleted = commentService.deleteComment(commentId);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
