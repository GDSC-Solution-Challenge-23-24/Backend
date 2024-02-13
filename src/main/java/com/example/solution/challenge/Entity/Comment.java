// Entity   Comment

package com.example.solution.challenge.Entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Entity
@Setter
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String content;

    @ManyToOne(targetEntity = Board.class)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    //depth
    @ColumnDefault("0")
    private Long depth;

    //parent-> 부모 댓글 id
    @ColumnDefault("0")
    private Long parent;

    @ColumnDefault("false")
    private Boolean isDeleted;
}