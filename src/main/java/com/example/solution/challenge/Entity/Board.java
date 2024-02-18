// Entity Board

package com.example.solution.challenge.Entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter

public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    private int board_no; // 다중 게시판 구현을 위한 게시판 넘버

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private int likes;
    private boolean IsPopular; // 실시간 인기글 여부

}