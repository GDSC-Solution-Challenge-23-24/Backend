// Controller  BoardController



package com.example.solution.challenge.Controller;

import com.example.solution.challenge.Dto.BoardDto;
import com.example.solution.challenge.Entity.Board;
import com.example.solution.challenge.Service.BoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

// Board API
@RequestMapping("/api/boards")

public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 모든 게시글 조회
    @GetMapping
    public List<Board> getAllBoards() {
        return boardService.getAllBoards();
    }

    // 새로운 게시글 생성
    @PostMapping
    public ResponseEntity<Board> createBoard(@RequestBody BoardDto boardDto) {
        Board newBoard = boardService.createBoard(boardDto);
        return ResponseEntity.ok(newBoard);
    }

    // 특정 ID의 게시글 조회
    @GetMapping("/{id}")
    public ResponseEntity<Board> getBoardById(@PathVariable Long id) {
        Optional<Board> board = boardService.getBoardById(id);
        return board.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 특정 ID의 게시글 업데이트 & 실시간 인기글
    @PutMapping("/{id}")
    public ResponseEntity<Board> updateBoard(@PathVariable Long id, @RequestBody BoardDto boardDto) {
        Board updatedBoard = boardService.updateBoard(id, boardDto);
        if (updatedBoard != null) {
            if (updatedBoard.getLikes() >= 10) {
                boardService.promoteToPopularBoard(updatedBoard.getId());
                return ResponseEntity.ok(updatedBoard);
            }
            return ResponseEntity.ok(updatedBoard);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 특정 ID의 게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.noContent().build();
    }
}