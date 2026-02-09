package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserResponseDto;

public interface UserService {

    UserResponseDto createUser(UserDto userDto);

    UserResponseDto findById(Long id);
}
