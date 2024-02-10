package com.example.solution.challenge.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.solution.challenge.Entity.Board;
import com.example.solution.challenge.Repository.BoardRepository;
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
    private final BoardRepository BoardRepository;
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

    // 소속 설정
    public boolean updateAffiliation(Long userId, String affiliation) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setAffiliation(affiliation);
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    // 비밀번호 변경
    public boolean updatePassword(Long userId, String newPassword) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setPassword(newPassword);
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    // 내가 작성한 글 보기
    public List<Board> getMyPosts(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return BoardRepository.findByUser(user);
    }

}