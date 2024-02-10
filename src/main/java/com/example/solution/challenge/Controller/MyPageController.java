package com.example.solution.challenge.Controller;

import java.util.List;

import com.example.solution.challenge.Service.MyPageService;
import com.example.solution.challenge.Service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.solution.challenge.Entity.Board;
import com.example.solution.challenge.Entity.Comment;
import com.example.solution.challenge.Entity.User;

import lombok.RequiredArgsConstructor;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mypage")

public class MyPageController {
    private final MyPageService myPageService;
    private final UserService userService;

    // 소속 설정
    @PostMapping("/update-affiliation")
    public ResponseEntity<String> updateAffiliation(@RequestParam Long userId,
                                                    @RequestParam String affiliation) {
        boolean updated = myPageService.updateAffiliation(userId, affiliation);
        if (updated) {
            return new ResponseEntity<>("Affiliation updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    // 닉네임 수정
    @PostMapping("/update-nickname")
    public ResponseEntity<String> updateNickname(@RequestParam Long userId,
                                                 @RequestParam String newNickname) {
        boolean updated = myPageService.updateNickname(userId, newNickname);
        if (updated) {
            return new ResponseEntity<>("success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    // 비밀번호 변경
    @PostMapping("/update-password")
    public ResponseEntity<String> updatePassword(@RequestParam Long userId,
                                                 @RequestParam String newPassword) {
        boolean updated = myPageService.updatePassword(userId, newPassword);
        if (updated) {
            return new ResponseEntity<>("Password updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    // 내가 작성한 글 보기
    @GetMapping("/my-posts/{userId}")
    public ResponseEntity<List<Board>> getMyPosts(@PathVariable Long userId) {
        List<Board> myPosts = myPageService.getMyPosts(userId);
        return new ResponseEntity<>(myPosts, HttpStatus.OK);
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestParam Long userId) {
        boolean loggedOut = userService.logout(userId);
        if (loggedOut) {
            return new ResponseEntity<>("Logged out successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    // 회원 탈퇴
    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestParam Long userId) {
        boolean withdrawn = userService.withdraw(userId);
        if (withdrawn) {
            return new ResponseEntity<>("Withdrawn successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

}