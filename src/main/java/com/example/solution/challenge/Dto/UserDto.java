package com.example.solution.challenge.Dto;

import com.example.solution.challenge.Entity.UserGroup;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String username;
    private String password;
    private String email;
    private UserGroup userGroup;
}