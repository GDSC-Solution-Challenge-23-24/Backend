package com.example.solution.challenge.Service;


import org.springframework.stereotype.Service;

import com.example.solution.challenge.Dto.LikesDto;
import com.example.solution.challenge.Entity.Board;
import com.example.solution.challenge.Entity.Likes;
import com.example.solution.challenge.Entity.User;
import com.example.solution.challenge.Repository.BoardRepository;
import com.example.solution.challenge.Repository.LikesRepository;
import com.example.solution.challenge.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikesService {
    private final LikesRepository likeRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    //좋아요 등록
    public void createLike(LikesDto likeDto){
        User user=userRepository.findById(likeDto.getUserId()).orElseThrow(NullPointerException::new);
        Board board= boardRepository.findById(likeDto.getBoardId()).orElseThrow(NullPointerException::new);

        // 좋아요 없으면 등록
        if (likeRepository.findByUserAndBoard(user,board).isEmpty()){
            Likes newLike=new Likes();
            newLike.setBoard(board);
            newLike.setUser(user);
            likeRepository.save(newLike);
        }
    }

    //좋아요 취소
    public void deleteLike(LikesDto likeDto) {
        User user=userRepository.findById(likeDto.getUserId()).orElseThrow(NullPointerException::new);
        Board board= boardRepository.findById(likeDto.getBoardId()).orElseThrow(NullPointerException::new);

        //좋아요 있으면 취소
        if (likeRepository.findByUserAndBoard(user,board).isPresent()){
            likeRepository.deleteByUserAndBoard(user, board);
        }
    }

}