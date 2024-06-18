package com.fullstack.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.security.Timestamp;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "first_name", length = 20)
    private String firstName;

    @Column(name = "last_name", length = 20)
    private String lastName;

    @Column(name = "username", length = 50 , unique = true)
    private String userName;

    @Column(name = "email", length = 55 , unique = true)
    private String email;

    @Column(name = "password", length = 25)
    private String password;

    @Column(name = "created_at")
    private Timestamp created_at;
}
