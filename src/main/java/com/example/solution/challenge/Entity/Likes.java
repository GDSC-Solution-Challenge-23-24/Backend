package com.example.solution.challenge.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import jakarta.persistence.Entity;
//
//


@Entity
@Getter
@Setter
@Table(name = "likes")
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long likesId;

    @ManyToOne(targetEntity = Board.class)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;
}