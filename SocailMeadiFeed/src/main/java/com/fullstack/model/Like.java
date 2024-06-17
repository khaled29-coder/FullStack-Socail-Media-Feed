package com.fullstack.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "Likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post postid;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @Column(name = "likes")
    private int likes;

    @Column(name = "created_at")
    private LocalDateTime created_at;
}
