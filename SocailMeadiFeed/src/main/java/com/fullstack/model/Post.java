package com.fullstack.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @Column(name = "content" , nullable = false , columnDefinition = "TEXT")
    private String content;

    @Column(name = "image_url")
    private String image_url;

    @Column(name = "created_at")
    private LocalDateTime created_at;
}
