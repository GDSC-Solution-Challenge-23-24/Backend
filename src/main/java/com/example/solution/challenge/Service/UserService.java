package com.example.solution.challenge.Service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.example.solution.challenge.Dto.UserDto;
import com.example.solution.challenge.Entity.User;
import com.example.solution.challenge.Repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(UserDto userDto){
        String encryptedPassword = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt());

        User newUser = new User();
        newUser.setUsername(userDto.getUsername());
        newUser.setPassword(encryptedPassword);

        return userRepository.save(newUser);
    }

    public Optional<User> loginUser(UserDto userDto) {
        Optional<User> userOptional = userRepository.findByUsername(userDto.getUsername());

        return userOptional.filter(user -> checkPassword(userDto.getPassword(), user.getPassword()));
    }

    public boolean checkPassword(String plainPassword, String hashedPassword){
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

    public boolean logout(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setSessionId(null); // 세션 ID를 제거하여 로그아웃 상태로 변경
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    public boolean withdraw(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            userRepository.deleteById(userId); // 사용자를 데이터베이스에서 삭제하여 회원 탈퇴
            return true;
        } else {
            return false;
        }
    }

}