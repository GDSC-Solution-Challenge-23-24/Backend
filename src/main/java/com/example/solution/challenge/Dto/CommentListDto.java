package com.example.solution.challenge.Dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentListDto {
    private String content;
    private Long userId;
    private Long parent;
    private Long depth;
    private Boolean isDeleted;

    private List<CommentListDto> children;
}