package com.example.solution.challenge.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Type;


@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username", nullable = false)
    private String username;

    //이메일
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    //그룹 선택
    @Enumerated(EnumType.STRING)
    private UserGroup userGroup;


    @Column(name = "affiliation", nullable = false)
    private String affiliation;

    @Column(name = "session_id")
    private String sessionId;
}