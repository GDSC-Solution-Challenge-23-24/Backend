package com.example.solution.challenge.Controller;

import com.example.solution.challenge.Dto.UserDto;
import com.example.solution.challenge.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController

// User API
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {
        userService.registerUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 성공");
    }

    @PostMapping("/login")
    public Optional<ResponseEntity<String>> loginUser(@RequestBody UserDto userDto) {
        return userService.loginUser(userDto)
                .map(user -> ResponseEntity.ok("로그인 성공. 안녕하세요, " + userDto.getUsername()));

    }
    //.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("아이디나 비밀번호가 잘못되었습니다."));
}