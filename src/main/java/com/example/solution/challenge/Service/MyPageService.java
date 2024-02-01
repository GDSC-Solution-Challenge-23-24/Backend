package com.example.solution.challenge.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.solution.challenge.Entity.Board;
import com.example.solution.challenge.Repository.UserRepository;
import com.example.solution.challenge.Repository.LikesRepository;
import com.example.solution.challenge.Entity.Likes;
import com.example.solution.challenge.Entity.User;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final LikesRepository likeRepository;
    private final UserRepository userRepository;

    // 좋아요 글 목록 조회
    public List<Board> getLikedBoards(Long userId) {
        User user= userRepository.findById(userId).orElseThrow(NullPointerException::new);
        List<Likes> likedList=likeRepository.findByUser(user);
        return likedList.stream().map(Likes::getBoard).collect(Collectors.toList());
    }

    // 닉네임 수정
    public boolean updateNickname(Long userId, String newNickname) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setUsername(newNickname);
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }


}