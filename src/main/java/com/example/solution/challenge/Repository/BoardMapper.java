package com.example.solution.challenge.Repository;

import java.util.List;
import com.example.solution.challenge.Entity.Board;

public interface BoardMapper {
    // 게시판 등록
    public void enroll(Board board);

    // 게시판 목록
    public List<Board> getList();
}
