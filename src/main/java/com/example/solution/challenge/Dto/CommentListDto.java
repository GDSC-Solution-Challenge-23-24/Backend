// Dto CommentListDto
package com.example.solution.challenge.Dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentListDto {
    private String content;

    private Long userId;
    private Long parent; // 부모 댓글 (원댓글)
    private Long depth; // 깊이 = 대댓글

    private Boolean isDeleted; // 삭제 여부

    private List<CommentListDto> children; // 자식 댓글 (대댓글)
}