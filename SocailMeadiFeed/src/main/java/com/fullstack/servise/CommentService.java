package com.fullstack.servise;

import com.fullstack.model.Comment;
import com.fullstack.model.Post;
import com.fullstack.model.User;
import com.fullstack.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getCommentsByPost(Post post) {
        return commentRepository.findByPost(post);
    }

    public Comment createComment(Post post_id, User user_id, String content) {
        Comment comment = new Comment();
        comment.setPostId(post_id);
        comment.setUserId(user_id);
        comment.setContent(content);
        comment.setCreated_at(new Timestamp(System.currentTimeMillis())); // Current timestamp
        return commentRepository.save(comment);
    }

    public boolean deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (comment != null) {
            commentRepository.delete(comment);
            return true;
        }
        return false;
    }
}
