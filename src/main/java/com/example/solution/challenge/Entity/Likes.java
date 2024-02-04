package com.example.solution.challenge.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.Column;
//
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;

@Entity
@Getter
@Setter
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