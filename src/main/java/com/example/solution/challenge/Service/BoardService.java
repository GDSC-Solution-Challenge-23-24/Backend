// Service  BoardService

package com.example.solution.challenge.Service;


import com.example.solution.challenge.Dto.BoardDto;
import com.example.solution.challenge.Entity.Board;
import com.example.solution.challenge.Repository.BoardRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    // 새로운 게시글 생성
    public Board createBoard(BoardDto boardDto) {
        // BoardDto로부터 새로운 Board Entity 생성
        Board newBoard = new Board();
        newBoard.setTitle(boardDto.getTitle());
        newBoard.setContent(boardDto.getContent());

        // 생성된 Board Entity를 저장하고 리턴
        return boardRepository.save(newBoard);
    }

    // 모든 게시글 조회
    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    // 특정 ID의 게시글 조회
    public Optional<Board> getBoardById(Long id) {
        return boardRepository.findById(id);
    }

    // 게시글 업데이트
    public Board updateBoard(Long id, BoardDto boardDto) {

        // 해당 id의 게시물 찾기
        Optional<Board> optionalBoard = boardRepository.findById(id);

        // 게시글 존재 - 업데이트 수행
        if (optionalBoard.isPresent()) {
            Board existingBoard = optionalBoard.get();
            // 업데이트 내역 저장 후 리턴
            existingBoard.setTitle(boardDto.getTitle());
            existingBoard.setContent(boardDto.getContent());
            return boardRepository.save(existingBoard);
        }

        // 게시글 부재 - null 리턴 / 예외 처리
        return null;
    }

    // 게시글 삭제
    public void deleteBoard(Long id) {
        // 해당 id의 게시물 찾아서 삭제
        boardRepository.deleteById(id);
    }

    public void promoteToPopularBoard(Long id) {
        Board board = boardRepository.findById(id).orElse(null);
        if (board != null) {
            board.setIsPopular(true);
            boardRepository.save(board);
        }
    }

}