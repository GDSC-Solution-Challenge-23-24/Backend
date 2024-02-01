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
}